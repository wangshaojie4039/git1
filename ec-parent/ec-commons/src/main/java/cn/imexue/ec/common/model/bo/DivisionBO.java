package cn.imexue.ec.common.model.bo;

import java.io.Serializable;

/**
 * 区域对象，包括省市区
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年3月1日
 * @author lijianfeng
 * @version 1.0
 */
public class DivisionBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3504192318644016894L;
	private Long provinceId;
	private Long cityId;
	private Long districtId;
	
	public DivisionBO() {
	}
	
	public DivisionBO(Long provinceId, Long cityId, Long districtId) {
		super();
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
	}
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
}
