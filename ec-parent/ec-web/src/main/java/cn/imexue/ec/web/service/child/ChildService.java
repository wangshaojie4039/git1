package cn.imexue.ec.web.service.child;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.ExcelAppException;
import cn.imexue.ec.common.mapper.ec.CardMapper;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.ParentMapper;
import cn.imexue.ec.common.mapper.ec.XcChildMapper;
import cn.imexue.ec.common.model.Card;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.ParentVo;
import cn.imexue.ec.common.service.sys.SMSTaskManager;
import cn.imexue.ec.common.service.sys.SMSTencentService;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.attendance.school.SchoolAttendanceCardService;
import cn.imexue.ec.web.web.controller.child.req.ChildExcel;
import cn.imexue.ec.web.web.excel.ExcelException;

@Service
@Transactional(readOnly=true)
public class ChildService {

	@Resource
	private ChildMapper childMapper;
	
	@Resource
	private ParentMapper parentMapper;
	
	@Resource
	private CardMapper cardMapper;
	
	@Resource
	private ClassMapper classMapper;
	
	@Resource
	private ChildCardService childCardService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private SMSTencentService smsTencentService;
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private SMSTencentService sMSTencentService;
	
	@Resource
	private SchoolAttendanceCardService schoolAttendanceCardService;
	
	@Resource
	private XcChildMapper xcChildMapper;
	
	/**
	 * 
	 * @param param {name,schoolId,classId}
	 * @return
	 */
	public List<ChildVo> list(Map<String, Object> param){
		
		List<ChildVo> list = childMapper.pageList(param);
		list.forEach(x->x.setParents(parentMapper.getParentByChild(x.getId())));
		
		return list;
	}
	
	public ChildVo get(Long childId,Long schoolId){
		ChildVo child = childMapper.getChild(childId, schoolId);
		return child;
	}
	
	/**
	 * 
	 * @exception 3012 学生姓名重复
	 * @param child , batch 是否是导入
	 */
	@Transactional
	public void save(ChildVo child,boolean batch){
		//判断同一班级内名字不能重复
		String name = child.getName();
		Long classId = child.getClassId();
		
		String role = LoginUtil.getLoginInfo().getUserRole();
		Long schoolId = LoginUtil.getSchoolId();
		Long teacherId = LoginUtil.getLoginId();
		if (role.equals(Constants.APP_USER_ROLE_TEACHER)) {
			List<Long> classIdLists = classMapper.selectClassIdsByTeacherIdInSchool(teacherId,
					schoolId);
			if(!classIdLists.contains(classId)){
				throw new AppChkException(3118, "child.not.yourClass", child.getName());
			}
		}
		
		ChildVo childVo = childMapper.getByNameAndClass(name, classId);
		if(childVo==null){
			if(child.getId()==null){
				insert(child);
			}else{
				//修改了名字
				update(child);
			}
		}else{
			if(batch||(childVo.getId().equals(child.getId()))){
				//没修改名字
				child.setId(childVo.getId());
				update(child);
			}else{
				throw new AppChkException(3012, "children.name.duplication", child.getName());
			}
			
			// 通知家长加入班级
			if(childVo.getClassId().equals(child.getClass())){
				List<ParentVo> parents = parentMapper.getParentByChild(childVo.getChildId());
				parents.forEach(x->{
					
				String message = messageSource.getMessage("sms.parent.add.class",new Object[]{x.getName(),childVo.getClassName()},Locale.getDefault());
				smsTencentService.sendMessage(x.getMobile(), message, 0);
				});
				
			}
			
		}
		
	}
	
	/**
	 * 3013 没有找到幼儿
	 * @param childId
	 * @param schoolId
	 */
	@Transactional
	public void removeChild(Long childId,Long schoolId){
		ChildVo child = childMapper.getChild(childId, schoolId);
		if(child == null){
			throw new AppChkException(3013, "child.not.found");
		}
		//删除幼儿
		childMapper.deleteChild(childId);
		try{
			//删除幼儿和家长关系
			childMapper.deleteRelationShipByChild(childId);
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
		try{
			xcChildMapper.deleteXcChild(childId);
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
		//解除卡绑定
		Card card = new Card();
		card.setSchoolId(schoolId);
		card.setUserId(childId);
		try{
			cardMapper.updateChildCardToNull(card);
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
		schoolAttendanceCardService.removeAttendance(schoolId, childId);
		
	}
	
	@Transactional
	public void importChildren(List<ChildExcel> children,Long schoolId){
		
		//map用来判重
		Map<String, ChildExcel> checkName = new HashMap<>();
		String smsid = UUID.randomUUID().toString();
		try{
			for(ChildExcel ex : children){
				ChildExcel childExcel = checkName.get(ex.getName());
				if(childExcel!=null&&childExcel.getClassName().equals(ex.getClassName())){
					//如果有相同的幼儿姓名和班级
					throw new ExcelException("excel.same.child", ex.getName());
				}
				checkName.put(ex.getName(), ex);
				ChildVo child = new ChildVo();
				child.setName(ex.getName());
				String birthday = ex.getBirthday();
				if(birthday != null){
					Date date = DateUtil.formatStringToDay(birthday, "yyyy-MM-dd");
					//生日
					child.setBirthday(date);
				}
				ClassVo classVo = classMapper.getByName(null, ex.getClassName(), schoolId);
				if(classVo==null){
					//没找到班级
					throw new ExcelException("excel.noFound.class", ex.getClassName());
				}
				child.setSchoolId(schoolId);
				child.setClassId(classVo.getId());
				
				//身份证
				child.setIdCardNo(ex.getCardNo());
				//性别
				String sex = ex.getSex();
				child.setSex(getSex(sex));
				
				save(child, true);
				
					if(!StringUtil.isBlank(ex.getFatherName())){
						String fatherName = ex.getFatherName();
						String fatherMobile = ex.getFatherMobile();
						ParentVo parent = getParent(fatherName, fatherMobile, "BB", "爸爸");
						parentService.addParent(parent, child.getId(), schoolId,smsid);
					}
					if(!StringUtil.isBlank(ex.getMotherName())){
						String motherName = ex.getMotherName();
						String motherMobile = ex.getMotherMobile();
						ParentVo parent = getParent(motherName, motherMobile, "MM", "妈妈");
						parentService.addParent(parent, child.getId(), schoolId,smsid);
					}
					if(ex.getCardNo1() != null){
						childCardService.save(ex.getCardNo1(), child.getId(), schoolId);
					}
					if(ex.getCardNo2() != null){
						childCardService.save(ex.getCardNo2(), child.getId(), schoolId);
					}
					if(ex.getCardNo3() != null){
						childCardService.save(ex.getCardNo3(), child.getId(), schoolId);
					}
					if(ex.getCardNo4() != null){
						childCardService.save(ex.getCardNo4(), child.getId(), schoolId);
					}
					
			}
			//统一发送短信
			sMSTencentService.sendMessageFromQueue(smsid);
		}catch(AppChkException e){
			SMSTaskManager.removeFromQueue(smsid);
			throw new ExcelAppException(e,e.getMessage());
		}
	}
	
	/**
	 * 生成父母
	 * @param name
	 * @param mobile
	 * @return
	 */
	private ParentVo getParent(String name,String mobile,String code,String relation){
		ParentVo parent = new ParentVo();
		parent.setName(name);
		parent.setMobile(mobile);
		parent.setRelationCode(code);
		parent.setRelationName(relation);
		
		return parent;
	}
	
	private Byte getSex(String sex){
		switch (sex) {
		case "男":
			return (byte)1;
		case "女":
			return (byte)2;
		default:
			throw new ExcelException("excel.child.sex.error", sex);
		}
	}
	
	private void insert(ChildVo child){
		Long classId = child.getClassId();
		Map<String, Object> map = new HashMap<>();
		map.put("classId", classId);
		map.put("schoolId", child.getSchoolId());
		List<ChildVo> list = childMapper.pageList(map);
		if(list.size()>=80){
			throw new AppChkException(3096, "school.class.child.too.much", 80);
		}
		childMapper.insert(child);
	}
	
	private void update(ChildVo child){
		ChildVo ochild=childMapper.getChild(child.getId(), LoginUtil.getSchoolId());
		if(ochild!=null){
			child.setVersionNo(ochild.getVersionNo());
		}
		childMapper.update(child);
		schoolAttendanceCardService.updateAttendanceClass(child.getSchoolId(), child.getId(), child.getClassId());
	}
	
}
