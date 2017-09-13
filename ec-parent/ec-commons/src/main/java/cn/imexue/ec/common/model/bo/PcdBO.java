package cn.imexue.ec.common.model.bo;
/**
 * 省市区BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月28日
 * @author wangshaojie
 * @version 1.0
 */
public class PcdBO {

	private Long provinceId;
	
	private Long cityId;
	
	private Long districtId;

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		return "PcdBO [provinceId=" + provinceId + ", cityId=" + cityId
				+ ", districtId=" + districtId + "]";
	}
	
	
	
}
