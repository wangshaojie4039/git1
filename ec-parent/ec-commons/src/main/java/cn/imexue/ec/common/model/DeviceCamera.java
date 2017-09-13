package cn.imexue.ec.common.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import cn.imexue.ec.common.model.common.DataEntity;

public class DeviceCamera extends DataEntity {

    /**
     * 字段描述: [字段功能描述]
     */
    private static final long serialVersionUID = 6748504346055959972L;

    private Long customerId;

    private Long productId;

    private Long nvrId;

    private Long schoolId;

    private String schoolName;

    private Long classId;

    private String className;

    private String installLocation;

    @NotBlank(message = "{deviceNo.NotBlank}")
    private String deviceNo;

    @Range(max = 1, min = 0, message = "{name.Range}")
    private Byte isDefaultDeviceNo;

    private String validateCode;

    private Integer channelNo;

    @NotBlank(message = "{name.NotBlank}")
    private String name;

    private String model;

    private String imageUrl;

    private String orderNo;

    private Date confirmTime;

    private String openHours;

    private Byte isActive;

    private Date installTime;

    // /////////////////////以下为自定义字段////////////////////////////
    private String status; // 实时状态

    private String nvrName; // 硬盘录像机名

    private String schoolIds;

    private String judge; // 判断

    // /////////////////////以上为自定义字段////////////////////////////

    public Long getCustomerId() {

	return customerId;
    }

    public void setCustomerId(Long customerId) {

	this.customerId = customerId;
    }

    public Long getProductId() {

	return productId;
    }

    public void setProductId(Long productId) {

	this.productId = productId;
    }

    public Long getNvrId() {

	return nvrId;
    }

    public void setNvrId(Long nvrId) {

	this.nvrId = nvrId;
    }

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

    public Long getClassId() {

	return classId;
    }

    public void setClassId(Long classId) {

	this.classId = classId;
    }

    public String getClassName() {

	return className;
    }

    public void setClassName(String className) {

	this.className = className;
    }

    public String getInstallLocation() {

	return installLocation;
    }

    public void setInstallLocation(String installLocation) {

	this.installLocation = installLocation;
    }

    public String getDeviceNo() {

	return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {

	this.deviceNo = deviceNo;
    }

    public Byte getIsDefaultDeviceNo() {

	return isDefaultDeviceNo;
    }

    public void setIsDefaultDeviceNo(Byte isDefaultDeviceNo) {

	this.isDefaultDeviceNo = isDefaultDeviceNo;
    }

    public String getValidateCode() {

	return validateCode;
    }

    public void setValidateCode(String validateCode) {

	this.validateCode = validateCode;
    }

    public Integer getChannelNo() {

	return channelNo;
    }

    public void setChannelNo(Integer channelNo) {

	this.channelNo = channelNo;
    }

    public String getName() {

	return name;
    }

    public void setName(String name) {

	this.name = name;
    }

    public String getModel() {

	return model;
    }

    public void setModel(String model) {

	this.model = model;
    }

    public String getImageUrl() {

	return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

	this.imageUrl = imageUrl;
    }

    public String getOrderNo() {

	return orderNo;
    }

    public void setOrderNo(String orderNo) {

	this.orderNo = orderNo;
    }

    public Date getConfirmTime() {

	return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {

	this.confirmTime = confirmTime;
    }

    public String getOpenHours() {

	return openHours;
    }

    public void setOpenHours(String openHours) {

	this.openHours = openHours;
    }

    public Byte getIsActive() {

	return isActive;
    }

    public void setIsActive(Byte isActive) {

	this.isActive = isActive;
    }

    public Date getInstallTime() {

	return installTime;
    }

    public void setInstallTime(Date installTime) {

	this.installTime = installTime;
    }

    public String getStatus() {

	return status;
    }

    public void setStatus(String status) {

	this.status = status;
    }

    public String getNvrName() {

	return nvrName;
    }

    public void setNvrName(String nvrName) {

	this.nvrName = nvrName;
    }

    public String getSchoolIds() {

	return schoolIds;
    }

    public void setSchoolIds(String schoolIds) {

	this.schoolIds = schoolIds;
    }

    public String getJudge() {

	return judge;
    }

    public void setJudge(String judge) {

	this.judge = judge;
    }

    @Override
    public String toString() {

	return "DeviceCamera [customerId=" + customerId + ", productId=" + productId + ", nvrId=" + nvrId + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", classId="
		+ classId + ", className=" + className + ", installLocation=" + installLocation + ", deviceNo=" + deviceNo + ", isDefaultDeviceNo=" + isDefaultDeviceNo
		+ ", validateCode=" + validateCode + ", channelNo=" + channelNo + ", name=" + name + ", model=" + model + ", imageUrl=" + imageUrl + ", orderNo=" + orderNo
		+ ", confirmTime=" + confirmTime + ", openHours=" + openHours + ", isActive=" + isActive + ", installTime=" + installTime + ", status=" + status + ", nvrName="
		+ nvrName + ", schoolIds=" + schoolIds + "]";
    }

}
