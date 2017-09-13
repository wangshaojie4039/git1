package cn.imexue.ec.web.service.school;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataDuplicationException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.CardMapper;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.ParentMapper;
import cn.imexue.ec.common.mapper.ec.TeacherMapper;
import cn.imexue.ec.common.mapper.ec.XcChildMapper;
import cn.imexue.ec.common.model.Card;
import cn.imexue.ec.common.model.Child;
import cn.imexue.ec.common.model.ChildParentMap;
import cn.imexue.ec.common.model.Class;
import cn.imexue.ec.common.model.ClassTeacherMap;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassInfoVo;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.ParentVo;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.service.sys.SMSTencentService;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.attendance.school.SchoolAttendanceCardService;
import cn.imexue.ec.web.service.device.CameraService;

@Service
@Transactional(readOnly = true)
public class ClassService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private ClassMapper classMapper;

	@Resource
	private CardMapper cardMapper;

	@Resource
	private ChildMapper childMapper;

	@Resource
	private TeacherMapper teacherMapper;

	@Resource
	private CameraService cameraService;

	@Resource
	private ParentMapper parentMapper;

	@Resource
	private MessageSource messageSource;

	@Resource
	private SMSTencentService smsTencentService;

	@Resource
	private SchoolAttendanceCardService schoolAttendanceCardService;

	@Resource
	private XcChildMapper xcChildMapper;

	public List<ClassVo> pageList(Map<String, Object> map) {

		List<ClassVo> list = classMapper.pageList(map);
		for (ClassVo c : list) {
			List<TeacherVo> teachers = teacherMapper.getTeacherByClass(c
					.getId());
			// 移除班主任
			teachers.removeIf(t -> Constants.IS_ACTIVE.equals(t.getIsMaster()));
			c.setTeachers(teachers);
		}
		return list;
	}

	public List<ClassVo> getList(Long schoolId, Long teacherId) {

		Map<String, Object> map = new HashMap<>();
		map.put("schoolId", schoolId);
		map.put("teacherId", teacherId);
		map.put("orderBy", "name");
		map.put("orderType", "ASC");
		List<ClassVo> list = classMapper.pageList(map);
		return list;
	}

	public ClassVo getById(Long id) {

		ClassVo classVo = classMapper.getById(id);
		if (classVo == null) {
			throw new DataNoFountException("班级");
		}
		List<TeacherVo> teachers = teacherMapper.getTeacherByClass(classVo
				.getId());
		// 移除班主任
		teachers.removeIf(t -> Constants.IS_ACTIVE.equals(t.getIsMaster()));
		classVo.setTeachers(teachers);

		return classVo;
	}

	/**
	 * 保存 teachers不能为空 masterTeacherId 班主任id需要设置好
	 *
	 * @exception 1011 不能重复
	 * @param clazz
	 */
	@Transactional
	public void save(ClassVo clazz, boolean batch) {

		// TODO 班主任设置问题
		ClassVo classVo = classMapper.getByName(clazz.getId(), clazz.getName(),
				clazz.getSchoolId());
		if (classVo != null) {
			throw new AppChkException(3114, "class.name.duplication",
					clazz.getName());
		}
		if (clazz.getId() == null) {
			// 判断一个学校班级数不能超过100
			List<ClassInfoVo> list = classMapper.findAllBySchoolId(
					clazz.getSchoolId(), null);
			if (list.size() >= 100) {
				throw new AppChkException(3097, "school.class.too.much", 100);
			}
			classMapper.insertClass(clazz);
		} else {
			if (clazz.getMasterTeacherId() == null) {
				clazz.setMasterTeacherId(0l);
			}
			classMapper.updateClass(clazz);
			try {
				Assert.notNull(clazz.getId(), "学校Id不能为空");
				classMapper.deleteTeacherClassMap(clazz.getId(), null, null);
			} catch (MyBatisSystemException e) {
				if (e.getCause().getCause() instanceof AppChkException) {
					log.debug("没有删除老师班级");
				} else {
					throw e;
				}
			}
		}

		List<TeacherVo> teachers = clazz.getTeachers();
		if (clazz.getMasterTeacherId() != null) {

			boolean hasMaster = teachers.stream().anyMatch(
					t -> t.getId().equals(clazz.getMasterTeacherId()));
			if (!hasMaster) {
				// 班主任不在教师集合里
				TeacherVo teacherVo = teacherMapper.getTeacherById(
						clazz.getMasterTeacherId(), clazz.getSchoolId());
				teachers.add(teacherVo);
			}
			// if (!batch) {
			// TeacherVo teacherVo = teacherMapper.getTeacherById(
			// clazz.getMasterTeacherId(), clazz.getSchoolId());
			// if (teacherVo != null) {
			// teachers.add(teacherVo);
			// }
			// }
		}
		boolean flag = true;
		List<ClassTeacherMap> ctms = new ArrayList<>();
		for (TeacherVo t : teachers) {
			if (t == null) {
				continue;
			}
			ClassTeacherMap ctm = new ClassTeacherMap();
			ctm.setClassId(clazz.getId());
			ctm.setSchoolId(clazz.getSchoolId());

			ctm.setTeacherId(t.getId());
			/* if (!batch) { */
			// 是否是班主任
			if (flag && t.getId().equals(clazz.getMasterTeacherId())) {
				ctm.setIsMaster((byte) 1);
				flag = false;
			} else {
				ctm.setIsMaster((byte) 0);
			}
			/* } */

			ctm.setCreateTime(new Date());
			ctm.setCreatorId(LoginUtil.getLoginId());
			ctm.setCreatorRole(LoginUtil.getLoginInfo().getRole());

			ctms.add(ctm);
		}
		if (ctms.size() > 0) {
			classMapper.insertTeacherClassMap(ctms);
		}
	}

	/**
	 * 升班 name
	 *
	 * @param clazz
	 */
	@Transactional
	public void promote(Class clazz) {

		ClassVo classVo = classMapper.getByName(clazz.getId(), clazz.getName(),
				clazz.getSchoolId());
		if (classVo != null) {
			throw new DataDuplicationException("班级名");
		}
		clazz.setLastPromoteTime(new Date());
		update(clazz);
	}

	/**
	 * 毕业 is_graduate
	 *
	 * @param clazz
	 */
	@Transactional
	public void graduate(Class clazz) {

		// 毕业学生
		Child c = new Child();
		Class cls = classMapper.getById(clazz.getId());
		c.setSchoolId(clazz.getSchoolId());
		c.setClassId(clazz.getId());

		// 删除卡绑定
		Map<String, Object> param = new HashMap<>();
		param.put("schoolId", clazz.getSchoolId());
		param.put("classId", clazz.getId());
		List<ChildVo> list = childMapper.pageList(param);
		
		
		try {
			childMapper.graduateChildren(c);
		} catch (MyBatisSystemException e) {
			Throwable cause = e.getCause().getCause();
			if (!(cause instanceof AppChkException)) {
				throw e;
			}
		}
		

		for (ChildVo ch : list) {
			try {
				Card card = new Card();
				card.setSchoolId(clazz.getSchoolId());
				card.setUserId(ch.getId());
				cardMapper.updateChildCardToNull(card);
			} catch (MyBatisSystemException e) {
				Throwable cause = e.getCause().getCause();
				if (!(cause instanceof AppChkException)) {
					throw e;
				}
			}

			try {
				xcChildMapper.deleteXcChild(ch.getId());
			} catch (MyBatisSystemException e) {
				Throwable cause = e.getCause().getCause();
				if (!(cause instanceof AppChkException)) {
					throw e;
				}
			}
		}
		
		List<ChildParentMap> cpms = childMapper
				.selectRelationShipByClassId(clazz.getId());
		clazz.setIsGraduate((byte) 1);
		update(clazz);
		dismiss(clazz, false);
		cameraService.updateDeviceByClassId(clazz.getId());
		// TODO 家长推送
		// 发送短信
		sendParentLiveClassSMS(cpms, cls.getName(),
				"sms.parent.live.class.other", "班级毕业");
	}

	/**
	 * 解散 id schoolId
	 *
	 * @param clazz
	 */
	@Transactional
	public void dismiss(Class clazz, boolean isSendSMS) {

		ClassVo cls = classMapper.getById(clazz.getId());
		
//		cls.setMasterTeacherId(0l);
//		classMapper.updateClass(cls);
		try {
			Assert.notNull(clazz.getId(), "学校Id不能为空");
			// 删除教师
			classMapper.deleteTeacherClassMap(clazz.getId(), null, null);
		} catch (MyBatisSystemException e) {
			Throwable cause = e.getCause().getCause();
			if (!(cause instanceof AppChkException)) {
				throw e;
			}
		}

		// 删除卡绑定
		Map<String, Object> param = new HashMap<>();
		param.put("schoolId", clazz.getSchoolId());
		param.put("classId", clazz.getId());
		List<ChildVo> list = childMapper.pageList(param);

		for (ChildVo c : list) {
			try {
				Card card = new Card();
				card.setSchoolId(clazz.getSchoolId());
				card.setUserId(c.getId());
				cardMapper.updateChildCardToNull(card);
			} catch (MyBatisSystemException e) {
				Throwable cause = e.getCause().getCause();
				if (!(cause instanceof AppChkException)) {
					throw e;
				}
			}

			try {
				xcChildMapper.deleteXcChild(c.getId());
			} catch (MyBatisSystemException e) {
				Throwable cause = e.getCause().getCause();
				if (!(cause instanceof AppChkException)) {
					throw e;
				}
			}
		}
		try {
			// 删除幼儿
			childMapper.deleteByClass(clazz.getId());
		} catch (MyBatisSystemException e) {
			Throwable cause = e.getCause().getCause();
			if (!(cause instanceof AppChkException)) {
				throw e;
			}
		}

		List<ChildParentMap> cpms = childMapper
				.selectRelationShipByClassId(clazz.getId());
		if (cpms.size() > 0) {
			// 删除幼儿和家长的关系
			childMapper.deleteRelationShip(clazz.getId());
		}
		schoolAttendanceCardService.removeAttendanceByClass(
				clazz.getSchoolId(), clazz.getId());

		// TODO 家长推送
		// 发送家长从班级移除的短信
		if (isSendSMS) {
			sendParentLiveClassSMS(cpms, cls.getName(),
					"sms.parent.live.class.common", null);
		}

	}

	/**
	 * 解散并删除 is_delete
	 *
	 * @param clazz
	 */
	@Transactional
	public void dismissAndDelete(Class clazz) {

		Class cls = classMapper.getById(clazz.getId());
		List<ChildParentMap> cpms = childMapper
				.selectRelationShipByClassId(clazz.getId());
		dismiss(clazz, false);
		update(clazz);
		cameraService.updateDeviceByClassId(clazz.getId());
		// 发送家长从班级移除的短信
		sendParentLiveClassSMS(cpms, cls.getName(),
				"sms.parent.live.class.other", "班级解散");
	}

	/**
	 * 更新 升班需要设置name,last_promote_time 毕业 is_graduate 删除班级 is_delete
	 *
	 * @param clazz
	 */
	private void update(Class clazz) {

		classMapper.updateClass(clazz);
	}

	List<Long> getClassIdsByTeacherIdInSchool(Long teacherId, Long schoolId) {

		return null;

	}

	/**
	 * 方法描述: [获取班级基本信息列表.]</br> 初始作者: zhongxp<br/>
	 * 创建日期: 2017年7月3日-下午6:20:48<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param schoolId
	 * @return List<ClassInfoVo>
	 */
	public List<ClassInfoVo> findAllBySchoolId(Long schoolId) {

		Long teacherId = null;
		if (!LoginUtil.getLoginInfo().getUserRole()
				.equals(Constants.APP_USER_ROLE_SCHOOL_LEADER)) {
			teacherId = LoginUtil.getLoginId();
		}
		return classMapper.findAllBySchoolId(schoolId, teacherId);
	}

	private void sendParentLiveClassSMS(List<ChildParentMap> cpms,
			String className, String sms, String reason) {

		if (cpms.size() > 0) {
			for (ChildParentMap cpm : cpms) {
				ParentVo parent = parentMapper.getParentById(cpm.getParentId());
				String message = "";
				if ("sms.parent.live.class.common".equals(sms)) {
					message = messageSource.getMessage(
							"sms.parent.live.class.common",
							new Object[] { className }, Locale.getDefault());
				} else if ("sms.parent.live.class.other".equals(sms)) {

					message = messageSource.getMessage(
							"sms.parent.live.class.other", new Object[] {
									className, reason }, Locale.getDefault());
				}
				smsTencentService.sendMessage(parent.getMobile(), message, 0);
			}
		}
	}

	/**
	 * 
	 *
	 * 方法描述: [获取班级id列表.]<br/>
	 * 初始作者: 崔业新<br/>
	 * 创建日期: 2017年8月15日-下午3:47:39<br/>
	 * 开始版本: 1.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 * 
	 * @param teacherId
	 * @param schoolId
	 * @return List<Long>
	 *
	 */
	public List<Long> getClassListByTeachId(Long teacherId, Long schoolId) {

		return classMapper.selectClassIdsByTeacherIdInSchool(teacherId,
				schoolId);

	}
}
