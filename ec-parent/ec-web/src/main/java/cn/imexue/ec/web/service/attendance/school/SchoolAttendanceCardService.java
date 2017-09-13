package cn.imexue.ec.web.service.attendance.school;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.eccard.ChildAttendanceMapper;
import cn.imexue.ec.common.model.vo.ChildAttendanceDto;

@Service
@Transactional(transactionManager="eccardTransactionManager"
,propagation=Propagation.REQUIRES_NEW)
public class SchoolAttendanceCardService{

	@Resource(name="eccardSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public List<ChildAttendanceDto> getSchoolAttendance(Long schoolId,String date,Long classId,Boolean normal){
		ChildAttendanceMapper mapper = sqlSession.getMapper(ChildAttendanceMapper.class);
		return mapper.getSchoolAttendance(schoolId, date, classId,normal);
	}
	
	public void removeAttendance(Long schoolId,Long childId){
		ChildAttendanceMapper mapper = sqlSession.getMapper(ChildAttendanceMapper.class);
		try{
			mapper.removeAttendance(schoolId, childId);
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
	}
	
	public void updateAttendanceClass(@Param("schoolId")Long schoolId,@Param("childId")Long childId,@Param("classId")Long classId){
		ChildAttendanceMapper mapper = sqlSession.getMapper(ChildAttendanceMapper.class);
		try{
			mapper.updateAttendanceClass(schoolId, childId, classId);
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
		
	}

	public void removeAttendanceByClass(@Param("schoolId")Long schoolId,@Param("classId")Long classId){
		ChildAttendanceMapper mapper = sqlSession.getMapper(ChildAttendanceMapper.class);
		try{
			mapper.removeAttendanceByClass(schoolId, classId);
		}catch(MyBatisSystemException e){
			if(e.getCause().getCause() instanceof AppChkException){}
			else{throw e; }
		}
	}
	
}
