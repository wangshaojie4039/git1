
package cn.imexue.ec.common.model.bo;
/**
 * 搜索学校BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年3月23日
 * @author wangshaojie
 * @version 1.0
 */
public class SchoolSearchBO {

	private Long schoolId;
	
	private String schoolName;
	
	private String province;
	
	private String city;
	
	private String district;

	private String role;
	
	private Long provinceId;
	
	private Long cityId;
	
	private Long districtId;
	
	
	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	@Override
	public String toString() {
		return "SchoolSearchBO [schoolId=" + schoolId + ", schoolName="
				+ schoolName + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", role=" + role + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", districtId="
				+ districtId + "]";
	}



	
	
	
	
}
