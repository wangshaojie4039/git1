package cn.imexue.ec.common.mapper.ec;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.vo.SchoolVo;

@Mapper
public interface SchoolMapper {

	SchoolVo getSchoolById(@Param("schoolId")Long schoolId,@Param("cdd")Boolean cdd);

	void updateSchool(School school);

	void updateSchoolSetting(School school);

	Integer getTeacherNum(Long schoolId);

	Integer getParentNum(Long schoolId);

	Integer getChildrenNum(Long schoolId);

	School getById(Long id);

}
