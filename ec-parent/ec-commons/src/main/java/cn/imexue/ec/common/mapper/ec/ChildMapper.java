package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.Child;
import cn.imexue.ec.common.model.ChildParentMap;
import cn.imexue.ec.common.model.vo.ChildRideExtVo;
import cn.imexue.ec.common.model.vo.ChildVo;

@Mapper
public interface ChildMapper extends BaseMapper<ChildVo> {

	/**
	 * 按班级删除幼儿
	 *
	 * @param classId
	 */
	void deleteByClass(Long classId);

	/**
	 * 删除幼儿和家长关系
	 *
	 * @param classId
	 */
	void deleteRelationShip(Long classId);
	
	

	/**
	 * @param map
	 *            {name,schoolId,classId}
	 * @return
	 */
	@Override
	List<ChildVo> pageList(Map<String, Object> map);

	/**
	 * 毕业 只要设置classId
	 *
	 * @param c
	 */
	void graduateChildren(Child c);

	ChildVo selectById(@Param("id") Long id);

	/**
	 * @param childId
	 * @param schoolId
	 * @return
	 */
	ChildVo getChild(@Param("childId") Long childId, @Param("schoolId") Long schoolId);

	void insert(Child child);

	void update(Child child);

	ChildVo getByNameAndClass(@Param("name") String name, @Param("classId") Long classId);

	void deleteChild(Long childId);

	void deleteRelationShipByChild(Long childId);

	List<ChildVo> getByParent(@Param("parentId") Long parentId, @Param("schoolId") Long schoolId);

	List<ChildVo> selectChildList(Map<String, Object> param);

	List<ChildRideExtVo> selecChildByClassId(Map<String, Object> param);
	
	/**
	 * 
	 * 方法描述: [根据班级id查询幼儿与家长的关系]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年8月2日-下午5:04:23<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param classId
	 * @return
	 * List<ChildParentMap>
	 *
	 */
	List<ChildParentMap> selectRelationShipByClassId(Long classId);
	
	/**
	 * 
	 *
	 * 方法描述: [根据幼儿和家长id搜搜关系表]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年8月2日-下午5:45:11<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param childId
	 * @param parentId
	 * @return
	 * ChildParentMap
	 *
	 */
	ChildParentMap selectRelationShipByCPId(@Param("childId") Long childId,@Param("parentId") Long parentId);
}
