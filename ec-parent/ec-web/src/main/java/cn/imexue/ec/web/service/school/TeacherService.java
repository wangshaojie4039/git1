package cn.imexue.ec.web.service.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataDuplicationException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.exception.ExcelAppException;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.SchoolMapper;
import cn.imexue.ec.common.mapper.ec.TeacherMapper;
import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.Teacher;
import cn.imexue.ec.common.model.TeacherSchoolMap;
import cn.imexue.ec.common.model.bo.sms.SmsMessageBO;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.TeacherInfoVo;
import cn.imexue.ec.common.model.vo.TeacherTransVo;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.service.sys.SMSTaskManager;
import cn.imexue.ec.common.service.sys.SMSTencentService;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.Md5Util;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.web.controller.school.req.TeacherExcel;
import cn.imexue.ec.web.web.excel.ExcelException;

@Service
@Transactional(readOnly = true)
public class TeacherService {

	@Resource
	private TeacherMapper teacherMapper;

	@Resource
	private ClassMapper	classMapper;

	@Resource
	private ClassService classService;

	@Resource
	private MessageSource messageSource;

	@Resource
	private SMSTencentService smsTencentService;
	
	@Resource
	private SchoolMapper schoolMapper;
	
	@Resource
	private SMSTencentService sMSTencentService;

	/**
	 * 分页
	 *
	 * @param map
	 *            { mobile schoolId }
	 * @return
	 */
	public List<TeacherVo> pageList(Map<String, Object> map) {

		List<TeacherVo> list = teacherMapper.pageList(map);
		for (TeacherVo t : list) {
			List<ClassVo> classes = classMapper.getClassByTeacher(t.getId());
			t.setClasses(classes);
		}
		return list;
	}

	/**
	 * 根据id获取教师
	 *
	 * @param id
	 * @param schoolId
	 * @return
	 */
	public TeacherVo getTeacherById(Long id, Long schoolId) {

		TeacherVo teacher = teacherMapper.getTeacherById(id, schoolId);
		if (teacher == null) {
			throw new DataNoFountException("老师");
		}
		return teacher;
	}

	/**
	 * 根据手机号查询教师
	 *
	 * @param mobile
	 * @param schoolId
	 * @exception 1011 手机号重复
	 * @return
	 */
	public TeacherVo getTeacherByMobile(String mobile, Long schoolId) {

		TeacherVo teacher = teacherMapper.getTeacherByUsername(mobile);
		if (teacher == null) {
			return new TeacherVo();
		}
		Long schoolId2 = teacher.getSchoolId();
		TeacherVo teacherVo = teacherMapper.getTeacherById(teacher.getId(), schoolId);
		if (schoolId2 == null) {
			return teacher;
		} else if (schoolId2.equals(schoolId)) {
			return teacherVo;
		}
		throw new AppChkException(3067,"teacher.other.school");
	}

	/**
	 * @exception 3001 老师不存在,请刷新后再试
	 * @exception 3002 无权操作老师
	 */
	@Transactional
	public void save(Teacher teacher, String role, Long schoolId, boolean batch,String smsid) {

		if (batch) {
			// 如果是批量插入则先插入id
			String mobile = teacher.getMobile();
			TeacherVo vo = teacherMapper.getTeacherByUsername(mobile);
			if (vo != null) {
				teacher.setId(vo.getId());
				teacher.setVersionNo(vo.getVersionNo());
			}
		}

		Long id = teacher.getId();
		if (id == null) {
			// 新增
			insert(teacher, role, schoolId,smsid);
		} else {
			// 更新
			// 如果id不存在，则保存
			TeacherVo t = teacherMapper.getTeacherById(id, null);
			if (t == null) {
				throw new AppChkException(3001, "teacher.id.noexist", id);
			}
			// id存在，判断是否为该学校或者没有学校
			if (t.getSchoolId() != null && !schoolId.equals(t.getSchoolId())) {
				throw new AppChkException(3002, "teacher.noauth", t.getMobile());
			}
			if (t.getSchoolId() == null) {
				TeacherSchoolMap teacherSchoolMap = new TeacherSchoolMap();
				teacherSchoolMap.setCreateTime(new Date());
				teacherSchoolMap.setCreatorId(LoginUtil.getLoginId());
				teacherSchoolMap.setCreatorRole(LoginUtil.getLoginInfo().getRole());
				teacherSchoolMap.setRole(role);
				teacherSchoolMap.setSchoolId(schoolId);
				teacherSchoolMap.setTeacherId(teacher.getId());
				teacherMapper.insertTeacherSchool(teacherSchoolMap);
				// TODO 发送老师加入学校短信
				School school=schoolMapper.getById(schoolId);
				String message = messageSource.getMessage("sms.teacher.add.school", new Object[] { teacher.getMobile(), school.getName() }, Locale.getDefault());
				if(StringUtil.isNotEmpty(smsid)){
					SMSTaskManager.putToQueue(smsid, new SmsMessageBO(teacher.getMobile(), message, 0));
				}else{
					smsTencentService.sendMessage(teacher.getMobile(), message, 0);
				}
			}
			update(teacher, role, t.getSchoolId());
		}
	}

	/**
	 * 从学校移除
	 *
	 * @param teacherId
	 * @param schoolId
	 */
	@Transactional
	public void remove(Long teacherId, Long schoolId) {

		teacherMapper.removeTeacher(teacherId, schoolId);
		try {
			classMapper.deleteTeacherClassMap(null, teacherId, null);
			//清班级的班主任字段
			classMapper.deleteMasterId(teacherId);
		} catch (MyBatisSystemException e) {
			if (e.getCause().getCause() instanceof AppChkException) {
			}
			else {
				throw e;
			}
		}
	}

	/**
	 * @param username
	 *            手机号或者姓名 模糊查询
	 * @param schoolId
	 * @return
	 */
	public List<TeacherVo> query(String username, Long schoolId) {

		return teacherMapper.getTeacherByUsernameOrName(username, schoolId);
	}

	@Transactional
	public void importTeacher(List<TeacherExcel> teachers, Long schoolId) {

		// map用来判重班主任
		Map<String, TeacherExcel> checkName = new HashMap<>();
		List<TeacherVo> ts = new ArrayList<>();
		String smsid = UUID.randomUUID().toString();
		try{
		for (TeacherExcel ex : teachers) {
			if (ex.getClassName() != null) {
				TeacherExcel excel = checkName.get(ex.getClassName());
				if (excel != null && Constants.IS_ACTIVE.equals(excel.getIsMasterByte()) && Constants.IS_ACTIVE.equals(ex.getIsMasterByte())) {
					String className = excel.getClassName();
					ClassVo clazz = classMapper.getByName(null, className, schoolId);
					if (clazz == null) {
						throw new DataNoFountException("班级名:" + className);
					}
					// 如果有相同的班主任和班级
					throw new ExcelException("excel.same.teacher.master", ex.getClassName());
				}
				checkName.put(ex.getClassName(), ex);
			}

			TeacherVo teacher = new TeacherVo();
			teacher.setName(ex.getName());
			teacher.setBirthday(ex.getBirthdayDate());
			teacher.setMobile(ex.getMobile());
			teacher.setRole(ex.getSchoolRoleValue());
			teacher.setSex(ex.getSexValue());
			save(teacher, ex.getSchoolRoleValue(), schoolId, true,smsid);
			teacher.setClassName(ex.getClassName());
			teacher.setIsMaster(ex.getIsMasterByte());
			ts.add(teacher);
		}

		// 以下是班级逻辑
		// 首先去除没有填写班级的
		List<TeacherVo> collect = ts.stream().filter(x -> !StringUtil.isBlank(x.getClassName()))
				.collect(Collectors.toList());
		// 按班级分类
		Map<String, List<TeacherVo>> map = collect.stream().collect(
				Collectors.toMap(TeacherVo::getClassName, (p) -> Arrays.asList(p), (exist, newt) -> add(exist, newt)));
		// 更新老师班级绑定关系
		map.forEach((k, v) -> updateClass(k, v, schoolId));
		sMSTencentService.sendMessageFromQueue(smsid);
		}catch(AppChkException e){
			SMSTaskManager.removeFromQueue(smsid);
			throw new ExcelAppException(e, e.getMessage());
		}
	}

	private void updateClass(String className, List<TeacherVo> list, Long schoolId) {

		ClassVo clazz = classMapper.getByName(null, className, schoolId);
		if (clazz == null) {
			throw new DataNoFountException("班级名:" + className);
		}
		List<TeacherVo> teachers = teacherMapper.getTeacherByClass(clazz.getId());
		
		list = list.stream().collect(Collectors.toMap((x)->x.getId(),(x)->x,(a,b)->b))
		   .values().stream().collect(Collectors.toList());
		
		// 判断是否存在班主任
		boolean newMaster = list.stream().anyMatch(x -> Constants.IS_ACTIVE.equals(x.getIsMaster()));

		// 去重，如果教师是原来就有的则删除原来的
//		List<TeacherVo> collect = teachers.stream().filter(x -> {
//			// 如果excel里有新班主任则以新班主任为准
//				if (newMaster && Constants.IS_ACTIVE.equals(x.getIsMaster())) {
//					return false;
//				}
//				for (TeacherVo t2 : list) {
//					if (t2.getIsMaster() != null && x.getId().equals(t2.getId())) {
//						return false;
//					}
//				}
//				return true;
//			}).collect(Collectors.toList());
//		collect.addAll(list);
//		clazz.setTeachers(list);
		
		//去重，如果教师是原来就有的则删除原来的
		List<TeacherVo> teacherNew = new ArrayList<>();
	http:for(TeacherVo t:teachers){
			if(newMaster&&Constants.IS_ACTIVE.equals(t.getIsMaster())){
				continue;
			}
			for(TeacherVo t2: list){
				if(t.getId().equals(t2.getId())){
					continue http;
				}
			}
			teacherNew.add(t);
//			如果excel里有新班主任则以新班主任为准
//			if(newMaster){
//				t.setIsMaster((byte)0);
//			}
		}
		teacherNew.addAll(list);
		
		
		
		clazz.setTeachers(teacherNew);

		
		Optional<TeacherVo> findFirst = teacherNew.stream().filter(x -> Constants.IS_ACTIVE.equals(x.getIsMaster())).findFirst();
		if (findFirst.isPresent()) {
			clazz.setMasterTeacherId(findFirst.get().getId());
		}else{
			clazz.setMasterTeacherId(0l);
		}
		classService.save(clazz, true);
	}

	private List<TeacherVo> add(List<TeacherVo> t1, List<TeacherVo> t2) {

		List<TeacherVo> t3 = new ArrayList<>();
		if (!(t1 instanceof ArrayList)) {
			CollectionUtils.addAll(t3, t1);
		} else {
			t3 = t1;
		}
		if (!CollectionUtils.isEmpty(t2)) {
			CollectionUtils.addAll(t3, t2);
		}
		return t3;
	}

	/**
	 * 插入
	 */
	@Transactional
	private void insert(Teacher teacher, String role, Long schoolId,String smsid) {

		String password = RandomStringUtils.random(6, "1234567890");
		teacher.setPassword(Md5Util.encrypt(password));
		// 按手机号查询用户，如果用户不存在则新增,如果用户在其他学校则报错
		// 如果用户存在则拉到本学校并更新信息
		TeacherVo t = teacherMapper.getTeacherByUsername(teacher.getMobile());
		if (t != null) {
			if (t.getSchoolId() != null && !schoolId.equals(t.getSchoolId())) {
				throw new DataDuplicationException("手机号");
			} else {
				teacher.setId(t.getId());
				teacher.setVersionNo(t.getVersionNo());
				update(teacher, role, schoolId);
			}
		} else {
			// 新增
			teacher.setUid("T" + teacher.getMobile());
			teacher.setUuid(UUID.randomUUID().toString());
			if(teacher.getSex()==null){
				teacher.setSex((byte)2);
			}
			teacherMapper.insertTeacher(teacher);
			TeacherSchoolMap teacherSchoolMap = new TeacherSchoolMap();
			teacherSchoolMap.setCreateTime(new Date());
			teacherSchoolMap.setCreatorId(LoginUtil.getLoginId());
			teacherSchoolMap.setCreatorRole(LoginUtil.getLoginInfo().getRole());
			teacherSchoolMap.setRole(role);
			teacherSchoolMap.setSchoolId(schoolId);
			teacherSchoolMap.setTeacherId(teacher.getId());
			teacherMapper.insertTeacherSchool(teacherSchoolMap);
			// TODO 发送密码短信
			String message = messageSource.getMessage("sms.teacher.open.software", new Object[] { teacher.getMobile(), password }, Locale.getDefault());
			if(StringUtil.isNotEmpty(smsid)){
				SMSTaskManager.putToQueue(smsid, new SmsMessageBO(teacher.getMobile(), message, 0));
			}else{
				smsTencentService.sendMessage(teacher.getMobile(), message, 0);
			}
			// TODO 发送老师加入班级短信
			School school=schoolMapper.getById(schoolId);
			String message1 = messageSource.getMessage("sms.teacher.add.school", new Object[] { teacher.getMobile(), school.getName() }, Locale.getDefault());
			if(StringUtil.isNotEmpty(smsid)){
				SMSTaskManager.putToQueue(smsid, new SmsMessageBO(teacher.getMobile(), message1, 0));
			}else{
				smsTencentService.sendMessage(teacher.getMobile(), message1, 0);
			}
			
		}
	}

	private void update(Teacher teacher, String role, Long schoolId) {

		teacherMapper.updateTeacher(teacher, role, schoolId);
		//修改关联表冗余字段
		try{
			teacherMapper.updateUserInfoForOtherTable(teacher.getId(), teacher.getName(), teacher.getLogoUrl());
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
	}

	// 生成6位数字密码
	@SuppressWarnings("unused")
	private String password() {

		int password = RandomUtils.nextInt(10000, 999999);
		String md5 = Md5Util.encrypt("" + password);
		return md5;
	}

	/**
	 * 方法描述: [班级基本信息列表.]</br>
	 * 初始作者: zhongxp<br/>
	 * 创建日期: 2017年7月3日-下午6:12:45<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param schoolId
	 * @return
	 *         List<TeacherInfoVo>
	 */
	public List<TeacherInfoVo> findAllBySchoolId(Long schoolId, String mobile) {

		List<TeacherInfoVo> list = teacherMapper.findAllBySchoolId(schoolId);
		list = list.stream().filter(x -> !mobile.equals(x.getMobile())).collect(Collectors.toList());
		return list;
	}

	/**
	 * 方法描述: [获取登录用户.]</br>
	 * 初始作者: zhongxp<br/>
	 * 创建日期: 2017年7月4日-上午10:20:29<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param Id
	 * @return
	 *         List<TeacherTransVo>
	 */
	public TeacherTransVo findUserById(Long id) {

		return teacherMapper.findUserById(id);
	}

}
