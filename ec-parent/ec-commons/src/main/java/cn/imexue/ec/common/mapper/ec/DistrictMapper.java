package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import cn.imexue.ec.common.model.vo.IdNameVO;

public interface DistrictMapper {
	
	/**
	 * 获得城市的所有区
	 * @param province
	 * @return
	 */
	List<IdNameVO> selectByCityId(Long cityId);
	
	List<IdNameVO> selectByIds(List<Long> districtIds);

	List<IdNameVO> selectAll();
}