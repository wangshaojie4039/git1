package cn.imexue.ec.common.mapper.ec;

import org.apache.ibatis.annotations.Mapper;

import cn.imexue.ec.common.model.vo.TeacherVo;

@Mapper
public interface LoginMapper {

	TeacherVo login(String username);
	
}
