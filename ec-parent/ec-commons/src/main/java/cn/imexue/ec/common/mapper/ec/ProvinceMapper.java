package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import cn.imexue.ec.common.model.vo.IdNameVO;

public interface ProvinceMapper{

	/**
	 * 获得代理商的代理省份
	 * 
	 * @return
	 */
	List<IdNameVO> selectAll();

	List<IdNameVO> selectAllOrderByName();
}
