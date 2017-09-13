package cn.imexue.ec.common.mapper.eccard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.vo.ChildAttendanceDto;

@Mapper
public interface ChildAttendanceMapper {

	List<ChildAttendanceDto> getSchoolAttendance(
			@Param("schoolId")Long schoolId,@Param("date")String date,@Param("classId")Long classId
			,@Param("normal")Boolean normal); 
	
	void removeAttendance(@Param("schoolId")Long schoolId,@Param("childId")Long childId);
	
	void updateAttendanceClass(@Param("schoolId")Long schoolId,@Param("childId")Long childId,@Param("classId")Long classId);

	void removeAttendanceByClass(@Param("schoolId")Long schoolId,@Param("classId")Long classId);
	
}
