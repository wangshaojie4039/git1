package cn.imexue.ec.web.service.school;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.DynamicClassMapMapper;
import cn.imexue.ec.common.mapper.ec.DynamicCommentMapper;
import cn.imexue.ec.common.mapper.ec.DynamicLikeMapper;
import cn.imexue.ec.common.mapper.ec.DynamicMapper;
import cn.imexue.ec.common.mapper.ec.TeacherSchoolMapMapper;
import cn.imexue.ec.common.model.Dynamic;
import cn.imexue.ec.common.model.DynamicClassMap;
import cn.imexue.ec.common.model.DynamicComment;
import cn.imexue.ec.common.model.TeacherSchoolMap;
import cn.imexue.ec.common.model.vo.DynamicVO;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.web.role.RoleType;

@Service
@Transactional(readOnly = true)
public class DynamicService {

	@Resource
	private DynamicMapper			dynamicMapper;

	@Resource
	private DynamicCommentMapper	dynamicCommentMapper;

	@Resource
	private DynamicLikeMapper		dynamicLikeMapper;

	@Resource
	private DynamicClassMapMapper	dynamicClassMapMapper;

	@Resource
	private ClassMapper				classMapper;
	
	@Resource
	private TeacherSchoolMapMapper	teacherSchoolMapMapper;

	@Autowired
	protected HttpSession			session;

	/**
	 * 获取列表分页数据
	 *
	 * @param map
	 *            {}
	 * @return
	 */
	public List<DynamicVO> pageList(Map<String, Object> map) {

		Long userId = LoginUtil.getLoginId();
		Long schoolId=LoginUtil.getSchoolId();
		String userRole = LoginUtil.getLoginInfo().getUserRole();
		if (map.get("endTime") != null) {
			map.put("endTime", DateUtil.addDaysToDate((Date) map.get("endTime"), 1));
		}

		if (RoleType.T.toString().equals(userRole)) {
			map.put("teacherId", userId);
		}
		List<DynamicVO> list=dynamicMapper.pageList(map);
		for(DynamicVO vo:list){
			//判断是否是园长
			if(Constants.APP_USER_ROLE_TEACHER.equals(vo.getUserRole())){
				TeacherSchoolMap tsm=teacherSchoolMapMapper.selectBySchoolIdAndTeacherId(schoolId, vo.getUserId());
				if(tsm!=null){
					if(Constants.SCHOOL_DIRECTOR_ROLE.equals(tsm.getRole()) ){
						vo.setIsDirector(Constants.YES_BYTE);
					}else{
						vo.setIsDirector(Constants.NO_BYTE);
					}
				}else{
					vo.setIsDirector(Constants.NO_BYTE);
				}
				
			}else{
				vo.setIsDirector(Constants.NO_BYTE);
			}
			//获得班级信息
			vo.setClassNames(dynamicClassMapMapper.selectClassNameByDynamicId(vo.getId()));

		}
		return dynamicMapper.pageList(map);
	}

	/**
	 * 根据id获取动态
	 *
	 * @param id
	 * @param schoolId
	 * @return
	 */
	public DynamicVO getById(Long id) {
		Long schoolId = LoginUtil.getSchoolId();
		DynamicVO dynamic = dynamicMapper.selectById(id);
		if (dynamic == null ||!dynamic.getSchoolId().equals(schoolId)) {
			throw new DataNoFountException("动态");
		}
		// 评论
		List<DynamicComment> comments = dynamicCommentMapper.selectByDynamicId(id);
		// 点赞人
		List<String> likeNames = dynamicLikeMapper.selectUserNameByDynamicId(id);
		dynamic.setComments(comments);
		dynamic.setLikeNames(likeNames);
		return dynamic;
	}

	/**
	 * 根据id删除(软删除)
	 *
	 * @param id
	 */
	@Transactional
	public void deleteById(Long id) {

		Dynamic dynamic = dynamicMapper.selectById(id);
		// 判断操作权限
		if (dynamic == null || !isReaderDynamic(dynamic)) {
			throw new DataNoFountException("动态");
		}
		Date currentTime = DateUtil.getCurrentTimestamp();
		dynamic.setId(id);
		dynamic.setIsDelete(Constants.YES_BYTE);
		dynamic.setDeleteTime(currentTime);
		dynamicMapper.updateByIdSelective(dynamic);
		try {
			dynamicClassMapMapper.deleteByDynamicId(id);
		} catch (Exception e) {
			
			
		}
		
	}

	/**
	 * 验证用户是否有动态的操作权限
	 *
	 * @return
	 */
	private boolean isReaderDynamic(Dynamic dynamic) {

		Boolean flag = false;
		Long schoolId = LoginUtil.getSchoolId();
		Long userId = LoginUtil.getLoginId();
		String userRole = LoginUtil.getLoginInfo().getUserRole();
		// 判断是否属于本学校
		if (dynamic.getSchoolId().equals(schoolId)) {
			// 判断是什么角色，如果是园长，则具有本学校所有的动态操作权限，如果是老师，则判断老师是否有这个权限
			if (RoleType.T.toString().equals(userRole)) {
				List<Long> directorIds=teacherSchoolMapMapper.selectDirectorIdBySchoolId(schoolId);
				if(Constants.APP_USER_ROLE_TEACHER.equals(dynamic.getUserRole())&&dynamic.getUserId().equals(userId)){
					//老师自己发的动态
					flag=true;
				}else if(Constants.APP_USER_ROLE_TEACHER.equals(dynamic.getUserRole())&&directorIds.contains(dynamic.getUserId())){
					//园长发的动态
					flag=false;
				}else{
					List<Long> classIds = classMapper.selectClassIdsByTeacherIdInSchool(userId, schoolId);
					List<DynamicClassMap> dynCls = dynamicClassMapMapper.selectByDynamicId(dynamic.getId());
					for (DynamicClassMap dc : dynCls) {
						if (classIds.contains(dc.getClassId())) {
							// 动态在老师所在的班级内，有操作权限
							flag = true;
							break;
						}
					}
				}
			} else {
				flag = true;
			}
		}
		return flag;
	}

}
