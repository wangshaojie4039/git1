package cn.imexue.ec.common.model;

import java.util.Date;

public class SchoolGroupRequest {
    private Long id;

    private Long provinceId;

    private Long cityId;

    private Long districtId;

    private String name;

    private String address;

    private String linkman;

    private String mobile;

    private Long customerId;

    private Byte isAgree;

    private Long creatorInfo;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Byte getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Byte isAgree) {
        this.isAgree = isAgree;
    }

    public Long getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(Long creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "SchoolGroupRequest [id=" + id + ", provinceId=" + provinceId
				+ ", cityId=" + cityId + ", districtId=" + districtId
				+ ", name=" + name + ", address=" + address + ", linkman="
				+ linkman + ", mobile=" + mobile + ", customerId=" + customerId
				+ ", isAgree=" + isAgree + ", creatorInfo=" + creatorInfo
				+ ", createTime=" + createTime + "]";
	}
    
}