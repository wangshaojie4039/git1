package cn.imexue.ec.common.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.common.DataEntity;

@Alias("school")
public class School extends DataEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3082135377486371137L;

	private Long provinceId;

    private Long cityId;

    private Long districtId;

    private Long schoolGroupId;

    private String name;

    private String address;

    private String linkman;

    private String tel;

    private String appScreenImg;

    private String logoUrl;

    private Date inSchoolTime;

    private Date outSchoolTime;

    private String recipeTypes;

    private String cameraOpenHours;

    private Integer smsBalance;

    private Byte isActive;

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

    public Long getSchoolGroupId() {
        return schoolGroupId;
    }

    public void setSchoolGroupId(Long schoolGroupId) {
        this.schoolGroupId = schoolGroupId;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAppScreenImg() {
        return appScreenImg;
    }

    public void setAppScreenImg(String appScreenImg) {
        this.appScreenImg = appScreenImg;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Date getInSchoolTime() {
        return inSchoolTime;
    }

    public void setInSchoolTime(Date inSchoolTime) {
        this.inSchoolTime = inSchoolTime;
    }

    public Date getOutSchoolTime() {
        return outSchoolTime;
    }

    public void setOutSchoolTime(Date outSchoolTime) {
        this.outSchoolTime = outSchoolTime;
    }

    public String getRecipeTypes() {
        return recipeTypes;
    }

    public void setRecipeTypes(String recipeTypes) {
        this.recipeTypes = recipeTypes;
    }

    public String getCameraOpenHours() {
        return cameraOpenHours;
    }

    public void setCameraOpenHours(String cameraOpenHours) {
        this.cameraOpenHours = cameraOpenHours;
    }

    public Integer getSmsBalance() {
        return smsBalance;
    }

    public void setSmsBalance(Integer smsBalance) {
        this.smsBalance = smsBalance;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

}