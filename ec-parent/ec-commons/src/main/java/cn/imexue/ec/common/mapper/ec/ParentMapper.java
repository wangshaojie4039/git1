package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.ChildParentMap;
import cn.imexue.ec.common.model.Parent;
import cn.imexue.ec.common.model.vo.ParentVo;

@Mapper
public interface ParentMapper {

	List<ParentVo> getParentByChild(Long childId);
	
	void deleteParentChild(@Param("parentId")Long parentId,@Param("childId")Long childId);
	
	ParentVo getParentByMobile(String mobile);
	
	void insertParent(Parent parent);
	
	void insertParentChild(ChildParentMap childParentMap);
	
	void updateChildClass(@Param("childId")Long childId,@Param("classId")Long classId);

	ParentVo getParentById(Long parentId);
	
	void updateParent(Parent parent);
	
	void updateParentChild(ChildParentMap map);
}
