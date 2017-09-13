package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.Teacher;
import cn.imexue.ec.common.model.TeacherSchoolMap;
import cn.imexue.ec.common.model.vo.TeacherInfoVo;
import cn.imexue.ec.common.model.vo.TeacherTransVo;
import cn.imexue.ec.common.model.vo.TeacherVo;

@Mapper
public interface TeacherMapper {

	/**
	 * @param map
	 *            { mobile schoolId }
	 * @return
	 */
	List<TeacherVo> pageList(Map<String, Object> map);

	List<TeacherVo> getTeacherByClass(Long classId);

	TeacherVo getTeacherById(@Param("id") Long id, @Param("schoolId") Long schoolId);

	TeacherVo getTeacherByUsername(String username);

	List<TeacherVo> getTeacherByUsernameOrName(@Param("username") String username, @Param("schoolId") Long schoolId);

	void insertTeacher(Teacher teacher);

	void insertTeacherSchool(TeacherSchoolMap teacherSchoolMap);

	void updateTeacher(@Param("teacher") Teacher teacher, @Param("role") String role, @Param("schoolId") Long schoolId);

	void removeTeacher(@Param("teacherId") Long teacherId, @Param("schoolId") Long schoolId);

	TeacherInfoVo getById(@Param("id") Long id);

	List<TeacherInfoVo> findAllBySchoolId(@Param("schoolId") Long schoolId);

	TeacherTransVo findUserById(@Param("id") Long id);
	
	/**
	 * 当用户信息发生变更时，需要同时更新其它表的冗余用户信息字段
	 * @param userId 用户ID
	 * @param userName 用户姓名 
	 * @param logoUrl 用户图像
	 */
	void updateUserInfoForOtherTable(@Param("userId")Long userId,@Param("userName")String userName,@Param("logoUrl")String logoUrl);

}
