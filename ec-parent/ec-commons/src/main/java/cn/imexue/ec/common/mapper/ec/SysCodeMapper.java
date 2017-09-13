package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.SysCode;

public interface SysCodeMapper {

	/**
	 * 查找指定代码类型的列表
	 *@param type 代码类型
	 *@return 代码列表
	 */
	List<SysCode> selectByType(String type);

	/**
	 * 根据代码类型和代码找到对应的值
	 *@param type 代码类型
	 *@param code 代码键
	 *@return 代码对象
	 */
	SysCode selectByTypeAndName(@Param("type")String type, @Param("code")String code);
	
}