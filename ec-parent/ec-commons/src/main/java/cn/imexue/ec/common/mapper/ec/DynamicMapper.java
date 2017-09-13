package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import cn.imexue.ec.common.model.Dynamic;
import cn.imexue.ec.common.model.vo.DynamicVO;




public interface DynamicMapper   {
	
	/**
	 * 分页列表
	 * @param map{schoolId,classIds,classId,userInfo,content,startTime,endTime}
	 * @return
	 */
	List<DynamicVO> pageList(Map<String, Object> map);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	DynamicVO selectById(Long id);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	void deleteById(Long id );
	
	/**
	 * 根据id修改动态
	 * @param dynamic 动态对象
	 */
	void updateByIdSelective(Dynamic dynamic);
}