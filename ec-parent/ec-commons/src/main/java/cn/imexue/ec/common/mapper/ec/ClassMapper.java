package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.Class;
import cn.imexue.ec.common.model.ClassTeacherMap;
import cn.imexue.ec.common.model.vo.ClassInfoVo;
import cn.imexue.ec.common.model.vo.ClassVo;

@Mapper
public interface ClassMapper {

	/**
	 * 根据教师id获取班级
	 *
	 * @param teacherId
	 * @return
	 */
	List<ClassVo> getClassByTeacher(Long teacherId);

	/**
	 * @param map
	 *            {schoolId}
	 * @return
	 */
	List<ClassVo> pageList(Map<String, Object> map);

	ClassVo getById(Long id);

	/**
	 * @param clazz
	 */
	void updateClass(Class clazz);

	/**
	 * @param clazz
	 */
	void insertClass(Class clazz);

	/**
	 * 删除绑定关系，如果master为空则全删，如果master不为空则班主任不删
	 *
	 * @param classId
	 */
	void deleteTeacherClassMap(@Param("classId") Long classId, @Param("teacherId") Long teacherId, @Param("master") Long master);

	void insertTeacherClassMap(List<ClassTeacherMap> list);

	ClassVo getByName(@Param("id") Long id, @Param("name") String name, @Param("schoolId") Long schoolId);

	/**
	 * 方法描述: [通过schoolId获取班级列表.]<br/>
	 * 初始作者: 崔业新<br/>
	 * 创建日期: 2017年6月19日-下午4:15:35<br/>
	 * 开始版本: 1.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param schoolId
	 * @return
	 *         List<ClassVo>
	 */
	List<ClassVo> getClassBySchoolId(@Param("schoolId") Long schoolId);

	/**
	 * 根据老师id获得老师所在班级的id(指定学校)
	 *
	 * @param teacherId
	 *            老师id
	 * @param schoolId学校id
	 * @return
	 */
	List<Long> selectClassIdsByTeacherIdInSchool(@Param("teacherId") Long teacherId, @Param("schoolId") Long schoolId);

	/**
	 * 方法描述: [根据SchoolD获取基本信息列表.]</br>
	 * 初始作者: zhongxp<br/>
	 * 创建日期: 2017年7月3日-下午6:21:43<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param schoolId
	 * @param teacherId
	 * @return
	 *         List<ClassInfoVo>
	 */
	List<ClassInfoVo> findAllBySchoolId(@Param("schoolId") Long schoolId, @Param("teacherId") Long teacherId);

	void deleteMasterId(Long masterId);

}
