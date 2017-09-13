package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import cn.imexue.ec.common.model.vo.IdNameVO;

public interface CityMapper{
	
	/**
	 * 获得省份的所有市
	 * @param province
	 * @return
	 */
	List<IdNameVO> selectByProvinceId(Long provinceId);
	
	List<IdNameVO> selectByIds(List<Long> cityIds);

	List<IdNameVO> selectAll();
}