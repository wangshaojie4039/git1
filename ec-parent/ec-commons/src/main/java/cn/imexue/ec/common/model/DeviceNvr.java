package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

public class DeviceNvr extends DataEntity {

    /**
     * 字段描述: [字段功能描述]
     */
    private static final long serialVersionUID = -1576416456294905960L;

    private Long id;

    private Long customerId;

    private Long productId;

    private Long schoolId;

    private String schoolName;

    private String deviceNo;

    private Byte isDefaultDeviceNo;

    private String deviceSecret;

    private String name;

    private String model;

    private String imageUrl;

    private String orderNo;

    private Date confirmTime;

    private Byte isActive;

    private Date installTime;

    // 增加字段
    private Integer cameraCnt; // 摄像头数

    private String cameraDesc; // 画面显示视频设备

    private String status; // 实时状态

    private String productName; // 产品类型

    private Long provinceId; // 省份ID

    private Long cityId; // 市ID

    private Long districtId; // 区ID

    private String provinceName; // 省份名称

    private String cityName; // 市名称

    private String districtName; // 区名称

    private String customerName; // 代理商名称

    private String customerMobile; // 代理商手机号码

    private String customerIdName; // 代理商ID和名称

    private String schoolIdName; // 学校ID和名称

    private String judge; // 判断

    public Integer getCameraCnt() {

	return cameraCnt;
    }

    public void setCameraCnt(Integer cameraCnt) {

	this.cameraCnt = cameraCnt;
    }

    public String getCameraDesc() {

	return cameraDesc;
    }

    public void setCameraDesc(String cameraDesc) {

	this.cameraDesc = cameraDesc;
    }

    public String getStatus() {

	return status;
    }

    public void setStatus(String status) {

	this.status = status;
    }

    public String getProductName() {

	return productName;
    }

    public void setProductName(String productName) {

	this.productName = productName;
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

    public String getCustomerName() {

	return customerName;
    }

    public void setCustomerName(String customerName) {

	this.customerName = customerName;
    }

    public String getCustomerMobile() {

	return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {

	this.customerMobile = customerMobile;
    }

    public String getCustomerIdName() {

	return customerIdName;
    }

    public void setCustomerIdName(String customerIdName) {

	this.customerIdName = customerIdName;
    }

    public String getSchoolIdName() {

	return schoolIdName;
    }

    public void setSchoolIdName(String schoolIdName) {

	this.schoolIdName = schoolIdName;
    }

    @Override
    public Long getId() {

	return id;
    }

    @Override
    public void setId(Long id) {

	this.id = id;
    }

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

    public String getDeviceSecret() {

	return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {

	this.deviceSecret = deviceSecret;
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

    public String getJudge() {

	return judge;
    }

    public void setJudge(String judge) {

	this.judge = judge;
    }

    @Override
    public String toString() {

	return "DeviceNvr [id=" + id + ", customerId=" + customerId + ", productId=" + productId + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", deviceNo="
		+ deviceNo + ", isDefaultDeviceNo=" + isDefaultDeviceNo + ", deviceSecret=" + deviceSecret + ", name=" + name + ", model=" + model + ", imageUrl=" + imageUrl
		+ ", orderNo=" + orderNo + ", confirmTime=" + confirmTime + ", isActive=" + isActive + ", installTime=" + installTime + ", cameraCnt=" + cameraCnt
		+ ", cameraDesc=" + cameraDesc + ", status=" + status + ", productName=" + productName + ", provinceId=" + provinceId + ", cityId=" + cityId + ", districtId="
		+ districtId + ", provinceName=" + provinceName + ", cityName=" + cityName + ", districtName=" + districtName + ", customerName=" + customerName
		+ ", customerMobile=" + customerMobile + ", customerIdName=" + customerIdName + ", schoolIdName=" + schoolIdName + "]";
    }

}
