package cn.imexue.ec.web.service.child;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.ParentMapper;
import cn.imexue.ec.common.mapper.ec.SchoolMapper;
import cn.imexue.ec.common.model.ChildParentMap;
import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.SysCode;
import cn.imexue.ec.common.model.bo.sms.SmsMessageBO;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.ParentVo;
import cn.imexue.ec.common.service.sys.SMSTaskManager;
import cn.imexue.ec.common.service.sys.SMSTencentService;
import cn.imexue.ec.common.service.sys.SysCodeService;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.Md5Util;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.login.LoginUtil;

@Service
@Transactional(readOnly=true)
public class ParentService {

	@Resource
	private ParentMapper parentMapper;
	
	@Resource
	private ChildMapper childMapper;
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private SMSTencentService smsTencentService;
	
	@Resource
	private ClassMapper classMapper;
	@Resource
	private SchoolMapper schoolMapper;
	
	public List<ParentVo> parentList(Long childId){
		List<ParentVo> list = parentMapper.getParentByChild(childId);
		return list;
	}
	
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public ParentVo getParentByMobile(Long childId,String mobile,Long schoolId){
		ParentVo parentVo = parentMapper.getParentByMobile(mobile);
		if(parentVo == null){
			return null;
		}
		if(!Constants.IS_ACTIVE.equals(parentVo.getIsActive())){
			throw new AppChkException(3015, "child.parent.not.active");
		}
		List<ChildVo> children = childMapper.getByParent(parentVo.getId(),null);
		boolean match = children.stream().anyMatch(x->!schoolId.equals(x.getSchoolId()));
		if(match){
			throw new AppChkException(3020, "child.parent.otherSchool", mobile);
		}
		Optional<ChildVo> first = children.stream().filter(x->x.getId().equals(childId)).findFirst();
		if(first.isPresent()){
			ChildVo childVo = first.get();
			parentVo.setRelationCode(childVo.getRelationCode());
			parentVo.setRelationName(childVo.getRelationName());
		}
		return parentVo;
	}
	
	public ParentVo getParent(Long parentId,Long schoolId){
		ParentVo parent = parentMapper.getParentById(parentId);
		List<ChildVo> list = childMapper.getByParent(parentId, schoolId);
		parent.setChildren(list);
		return parent;
	}
	
	/**
	 * @exception 1011 没找到幼儿
	 * @exception 3014 无效的关系码
	 * @exception 3015 家长被禁用
	 * @exception 3020 家长已在其他学校注册
	 * @exception 3021 家长已绑定该幼儿
	 * 
	 * @param parents
	 * @param childId
	 * @param schoolId
	 */
	@Transactional
	public void addParent(ParentVo parents,Long childId,Long schoolId,String smsid){
		ChildVo child = childMapper.getChild(childId, schoolId);
		if(child == null){
			throw new DataNoFountException("幼儿");
		}
		ParentVo parentVo = parentMapper.getParentByMobile(parents.getMobile());
		boolean match2 = false;
		String password="";
		if(parentVo==null){
			//没有找到家长，新建
			parents.setUid("P"+parents.getMobile());
			parents.setUuid(UUID.randomUUID().toString());
			List<SysCode> relList = SysCodeService.getChildRelList();
			int indexOf = 0;
			for(int i=0;i<relList.size();i++){
				if(relList.get(i).getCode().equals(parents.getRelationCode())){
					indexOf = i;
					break;
				}
			}
			if(indexOf == -1){
				throw new AppChkException(3014, "child.reladtionCode.invalid", parents.getRelationCode());
			}
			if(indexOf!=6){
				parents.setSex((byte)((indexOf)%2+1));
			}else{
				parents.setSex(null);
			}
			password = RandomStringUtils.random(6, "1234567890");
			parents.setPassword(Md5Util.encrypt(password));
			parentMapper.insertParent(parents);
		}else{
			if(!Constants.IS_ACTIVE.equals(parentVo.getIsActive())){
				throw new AppChkException(3015, "child.parent.not.active");
			}
			parents.setId(parentVo.getId());
			parents.setVersionNo(parentVo.getVersionNo());
			List<ChildVo> children = childMapper.getByParent(parentVo.getId(),null);
			boolean match = children.stream().anyMatch(x->!schoolId.equals(x.getSchoolId()));
			if(match){
				throw new AppChkException(3020, "child.parent.otherSchool", parents.getMobile());
			}
			parentMapper.updateParent(parents);
			//match2:该家长是否已经是该幼儿的家长
			match2 = children.stream().anyMatch(x->x.getId().equals(childId));
			
		}
		ChildParentMap cpm = new ChildParentMap();
		cpm.setChildId(childId);
		cpm.setSchoolId(schoolId);
		cpm.setClassId(child.getClassId());
		cpm.setParentId(parents.getId());
		cpm.setCreateTime(new Date());
		cpm.setIsDefault(0);
		//是否已经有默认家长
		List<ParentVo> parentByChild = parentMapper.getParentByChild(childId);
		if(!parentByChild.stream().filter(x->!x.getId().equals(parents.getId())).anyMatch(x->x.getIsDefault()==1)){
			//如果没有默认家长，则设置为默认家长
			cpm.setIsDefault(1);
		}
		//关系
		cpm.setRelationCode(parents.getRelationCode());
		String relationName ;
		if(SysCodeService.CHILD_REL_OTHER.equals(cpm.getRelationCode())){
			relationName = parents.getRelationName();
		}else{
			relationName = SysCodeService.getByTypeAndCode(SysCodeService.CHILD_REL,parents.getRelationCode());
		}
		if(StringUtil.isBlank(relationName)){
			relationName=StringUtil.EMPTY;
//			throw new AppChkException(3014, "child.reladtionCode.invalid", parents.getRelationCode());
		}
		cpm.setRelationName(relationName);
		Long loginId = LoginUtil.getLoginId();
		String role = LoginUtil.getLoginInfo().getRole();
		cpm.setCreatorId(loginId);
		cpm.setCreatorRole(role);
		if(!match2){
			parentMapper.insertParentChild(cpm);
			if(parentVo==null){
				//TODO 发送短信：新建账号
				String message = messageSource.getMessage("sms.parent.open.software",new Object[]{parents.getMobile(),password},Locale.getDefault());
				if(StringUtil.isNotEmpty(smsid)){
					SMSTaskManager.putToQueue(smsid, new SmsMessageBO(parents.getMobile(), message, 0));
				}else{
					smsTencentService.sendMessage(parents.getMobile(), message, 0);
				}
			}
			//TODO 发送短信：加入班级
			School school=schoolMapper.getById(child.getSchoolId());
			ClassVo cls=classMapper.getById(child.getClassId());
			String schoolClassName="";
			if(school!=null&&cls!=null){
				 schoolClassName=school.getName()+cls.getName();
			}
			String message = messageSource.getMessage("sms.parent.add.class",new Object[]{parents.getMobile(),schoolClassName},Locale.getDefault());
			if(StringUtil.isNotEmpty(smsid)){
				SMSTaskManager.putToQueue(smsid, new SmsMessageBO(parents.getMobile(), message, 0));
			}else{
				smsTencentService.sendMessage(parents.getMobile(), message, 0);
			}
		}else{
			parentMapper.updateParentChild(cpm);
		}
	}
	@Transactional
	public void removeParentChildRelationShip(Long parentId,Long childId){
		
		
		ChildParentMap cpm=childMapper.selectRelationShipByCPId(childId, parentId);
		parentMapper.deleteParentChild(parentId, childId);
		List<ParentVo> parentByChild = parentMapper.getParentByChild(childId);
		if(parentByChild.size()>0){
			boolean hasDefault = parentByChild.stream().anyMatch(x->x.getIsDefault()==1);
			if(!hasDefault){
				//删除的是默认家长，需要新设置一个默认家长
				Optional<ParentVo> findFirst = parentByChild.stream().findFirst();
				ParentVo parentVo = findFirst.get();
				
				ChildParentMap map = new ChildParentMap();
				map.setChildId(childId);
				map.setParentId(parentVo.getId());
				map.setIsDefault(1);
				map.setRelationCode(parentVo.getRelationCode());
				map.setRelationName(parentVo.getRelationName());
				parentMapper.updateParentChild(map);
			}
			
		}
		
		//TODO 发送短信:移除关系
		ParentVo parent=parentMapper.getParentById(parentId);
		
		ClassVo cls=classMapper.getById(cpm.getClassId());
		String message = messageSource.getMessage("sms.parent.live.class.common",new Object[]{cls.getName()},Locale.getDefault());
		smsTencentService.sendMessage(parent.getMobile(), message, 0);
	}
	
	@Transactional
	public void setDefault(Long parentId,Long childId ){
		
		List<ParentVo> parentByChild = parentMapper.getParentByChild(childId);
		
		Optional<ParentVo> findFirst = parentByChild.stream().filter(x->x.getId().equals(parentId)).findFirst();
		if(findFirst.isPresent()){
				//如果原来有默认家长
			parentByChild.stream().filter(x->x.getIsDefault()==1).forEach(parentVo->{
				
				ChildParentMap map = new ChildParentMap();
				map.setChildId(childId);
				map.setParentId(parentVo.getId());
				map.setIsDefault(0);
				map.setRelationCode(parentVo.getRelationCode());
				map.setRelationName(parentVo.getRelationName());
				parentMapper.updateParentChild(map);
			});
			
			ParentVo parentVo = findFirst.get();
			ChildParentMap map = new ChildParentMap();
			map.setChildId(childId);
			map.setParentId(parentId);
			map.setIsDefault(1);
			map.setRelationCode(parentVo.getRelationCode());
			map.setRelationName(parentVo.getRelationName());
			parentMapper.updateParentChild(map);
		}else{
			//家长和幼儿没有关系
			throw new AppChkException(3069, "parent.no.found");
		}
		
		
	}
	
}
