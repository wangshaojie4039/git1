package cn.imexue.ec.common.model;

import java.util.Date;

public class CustomerDelegateDivision {

	private Long	id;

	private Long	customerId;

	private Long	provinceId;

	private Long	cityId;

	private Long	districtId;

	private Byte	delegateLevel;
	
	private Byte isActive;

	private Byte	isDelete;

	private Date	deleteTime;

	private Date	createTime;

	private String	creatorRole;

	private Long	creatorId;

	private Date	updateTime;

	private String	updaterRole;

	private Long	updaterId;

	// 以下是自添加字段
	private String	customerInfo;

	private String	provinceName;

	private String	cityName;

	private String	districtName;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
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

	public Byte getDelegateLevel() {

		return delegateLevel;
	}

	public void setDelegateLevel(Byte delegateLevel) {

		this.delegateLevel = delegateLevel;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public Byte getIsDelete() {

		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {

		this.isDelete = isDelete;
	}

	public Date getDeleteTime() {

		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {

		this.deleteTime = deleteTime;
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	public String getCreatorRole() {

		return creatorRole;
	}

	public void setCreatorRole(String creatorRole) {

		this.creatorRole = creatorRole;
	}

	public Long getCreatorId() {

		return creatorId;
	}

	public void setCreatorId(Long creatorId) {

		this.creatorId = creatorId;
	}

	public Date getUpdateTime() {

		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

	public String getUpdaterRole() {

		return updaterRole;
	}

	public void setUpdaterRole(String updaterRole) {

		this.updaterRole = updaterRole;
	}

	public Long getUpdaterId() {

		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {

		this.updaterId = updaterId;
	}

	public String getCustomerInfo() {

		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {

		this.customerInfo = customerInfo;
	}

	public String getProvinceName() {

		return provinceName;
	}

	public void setProvinceName(String provinceName) {

		this.provinceName = provinceName;
	}

	public String getCityName() {

		return cityName;
	}

	public void setCityName(String cityName) {

		this.cityName = cityName;
	}

	public String getDistrictName() {

		return districtName;
	}

	public void setDistrictName(String districtName) {

		this.districtName = districtName;
	}

	@Override
	public String toString() {

		return "CustomerDelegateDivision [id=" + id + ", customerId=" + customerId + ", provinceId=" + provinceId + ", cityId=" + cityId + ", districtId=" + districtId
				+ ", delegateLevel=" + delegateLevel + ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + ", createTime=" + createTime + ", creatorRole=" + creatorRole
				+ ", creatorId=" + creatorId + ", updateTime=" + updateTime + ", updaterRole=" + updaterRole + ", updaterId=" + updaterId + ", customerInfo=" + customerInfo
				+ ", provinceName=" + provinceName + ", cityName=" + cityName + ", districtName=" + districtName + "]";
	}

}
