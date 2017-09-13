package cn.imexue.ec.common.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import cn.imexue.ec.common.model.common.DataEntity;

public class DeviceAttendance extends DataEntity {

    /**
     *
     */
    private static final long serialVersionUID = 4432588817540561098L;

    private Long customerId;

    private Long productId;

    private Long schoolId;

    private String schoolName;

    @NotBlank(message = "{deviceNo.NotBlank}")
    private String deviceNo;

    @Range(max = 1, min = 0, message = "{isDefaultDeviceNo.Range}")
    private Byte isDefaultDeviceNo;

    @NotBlank(message = "{deviceSecret.NotBlank}")
    private String deviceSecret;

    @NotBlank(message = "{DeviceAttendance.name.NotBlank}")
    private String name;

    private String model;

    private String imageUrl;

    private String orderNo;

    private Date confirmTime;

    private Byte isActive;

    private Date installTime;

    private String productType;

    /** 增加字段 */
    private String status; // 实时状态

    private String productName; // 产品类型

    private Long provinceId; // 省份ID

    private Long cityId; // 市ID

    private Long districtId; // 区ID

    private String provinceName; // 省份名称

    private String cityName; // 市名称

    private String districtName; // 区名称

    private String customerName; // 代理商名称

    private String judge; // 判断

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

    public String getStatus() {

	return status;
    }

    public void setStatus(String status) {

	this.status = status;
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

    public String getProductName() {

	return productName;
    }

    public void setProductName(String productName) {

	this.productName = productName;
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

    public String getJudge() {

	return judge;
    }

    public void setJudge(String judge) {

	this.judge = judge;
    }

    public String getProductType() {

	return productType;
    }

    public void setProductType(String productType) {

	this.productType = productType;
    }

}
