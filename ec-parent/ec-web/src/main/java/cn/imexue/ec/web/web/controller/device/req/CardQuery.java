package cn.imexue.ec.web.web.controller.device.req;

import cn.imexue.ec.common.model.page.PageQuery;

public class CardQuery extends PageQuery {

	private Long	provinceId;

	private Long	cityId;

	private Long	districtId;

	private String	customerName;

	private String	schoolName;

	private String	deviceStatus;

	private String	cardNo;

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

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public String getSchoolName() {

		return schoolName;
	}

	public void setSchoolName(String schoolName) {

		this.schoolName = schoolName;
	}

	public String getDeviceStatus() {

		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {

		this.deviceStatus = deviceStatus;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
