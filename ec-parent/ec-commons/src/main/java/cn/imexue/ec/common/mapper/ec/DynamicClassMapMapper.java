package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import cn.imexue.ec.common.model.DynamicClassMap;


public interface DynamicClassMapMapper  {
	
	
	/**
	 * 根据动态id查出记录
	 * @param dynamicId
	 * @return
	 */
	List<DynamicClassMap> selectByDynamicId(Long dynamicId);
	
	/**
	 * 
	 *
	 * 方法描述: [根据动态id删除.]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月14日-下午2:10:57<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param dynamicId
	 * void
	 *
	 */
	void deleteByDynamicId(Long dynamicId);
	
	/**
	 * 
	 *
	 * 方法描述: [根据动态id获得动态所属班级的名称]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月27日-下午7:11:12<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param dynamicId
	 * @return
	 * List<String>
	 *
	 */
	List<String> selectClassNameByDynamicId(Long dynamicId);
	
}