package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import cn.imexue.ec.common.model.StaffProvince;

public interface StaffProvinceMapper{

	/**
	 * 获取代理商所代理的区域
	 */
	List<StaffProvince> selectByStaffId(Long staffId);

}
