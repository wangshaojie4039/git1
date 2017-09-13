package cn.imexue.ec.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerOrderSnapshot {

	private Long		id;

	private Long		productId;

	private Long		orderId;

	private String		productName;

	private String		productModel;

	private String		productImageUrl;

	private String		productUnit;

	private Integer		num;

	private BigDecimal	price;

	private Date		createTime;

	private String		creatorRole;

	private Long		creatorId;
	// ////////////////以下为自定义字段////////////////////
	// 订单号
	private String		orderNo;
	// 校名
	private String		name;
	//
	private String		isActive;
	// 设备编号
	private String		deviceNo;
	// 快递公司单号
	private String		expressNo;
	// 快递公司名
	private String		expressCode;
	// 订单状态
	private Integer		orderStatus;
	// 学校Id
	private Long		schoolId;
	/* 收货时间 */
	private Date		confirmTime;
	/* 客户Id */
	private Long		customerId;
	/* 订单原始金额 */
	private BigDecimal	originalAmount;
	/* 订单的金额 */
	private BigDecimal	orderAmount;
	// 发货时间
	private Date		deliveryTime;
	// 下单人信息
	private String		creatorInfo;
	// 客户信息
	private String		customerInfo;
	// 客户方备注
	private String		customerMemo;
	// 付款凭据
	private String		paymentReceipt;
	// 客户类型
	private Byte		roleType;
	// 客户名字
	private String		customerName;
	// 客户shouji
	private String		customerMobile;
	// 收货地址
	private String		deliveryAddress;

	/* 客服留言 */
	private String		deliveryMemo;

	/* 客服留言 */
	private String		cancelReason;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public String getProductName() {

		return productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public String getProductModel() {

		return productModel;
	}

	public void setProductModel(String productModel) {

		this.productModel = productModel;
	}

	public String getProductImageUrl() {

		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {

		this.productImageUrl = productImageUrl;
	}

	public String getProductUnit() {

		return productUnit;
	}

	public void setProductUnit(String productUnit) {

		this.productUnit = productUnit;
	}

	public Integer getNum() {

		return num;
	}

	public void setNum(Integer num) {

		this.num = num;
	}

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
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

	public String getOrderNo() {

		return orderNo;
	}

	public void setOrderNo(String orderNo) {

		this.orderNo = orderNo;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getIsActive() {

		return isActive;
	}

	public void setIsActive(String isActive) {

		this.isActive = isActive;
	}

	public String getDeviceNo() {

		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {

		this.deviceNo = deviceNo;
	}

	public String getExpressNo() {

		return expressNo;
	}

	public void setExpressNo(String expressNo) {

		this.expressNo = expressNo;
	}

	public String getExpressCode() {

		return expressCode;
	}

	public void setExpressCode(String expressCode) {

		this.expressCode = expressCode;
	}

	public Integer getOrderStatus() {

		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {

		this.orderStatus = orderStatus;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public Date getConfirmTime() {

		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {

		this.confirmTime = confirmTime;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public BigDecimal getOrderAmount() {

		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {

		this.orderAmount = orderAmount;
	}

	public BigDecimal getOriginalAmount() {

		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {

		this.originalAmount = originalAmount;
	}

	public Date getDeliveryTime() {

		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {

		this.deliveryTime = deliveryTime;
	}

	public String getCreatorInfo() {

		return creatorInfo;
	}

	public void setCreatorInfo(String creatorInfo) {

		this.creatorInfo = creatorInfo;
	}

	public String getCustomerMemo() {

		return customerMemo;
	}

	public void setCustomerMemo(String customerMemo) {

		this.customerMemo = customerMemo;
	}

	public String getPaymentReceipt() {

		return paymentReceipt;
	}

	public void setPaymentReceipt(String paymentReceipt) {

		this.paymentReceipt = paymentReceipt;
	}

	public String getCustomerInfo() {

		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {

		this.customerInfo = customerInfo;
	}

	public Byte getRoleType() {

		return roleType;
	}

	public void setRoleType(Byte roleType) {

		this.roleType = roleType;
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

	public String getDeliveryAddress() {

		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {

		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryMemo() {

		return deliveryMemo;
	}

	public void setDeliveryMemo(String deliveryMemo) {

		this.deliveryMemo = deliveryMemo;
	}

	public String getCancelReason() {

		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {

		this.cancelReason = cancelReason;
	}

	@Override
	public String toString() {

		return "CustomerOrderSnapshot [id=" + id + ", productId=" + productId + ", orderId=" + orderId + ", productName=" + productName + ", productModel=" + productModel
				+ ", productImageUrl=" + productImageUrl + ", productUnit=" + productUnit + ", num=" + num + ", price=" + price + ", createTime=" + createTime + ", creatorRole="
				+ creatorRole + ", creatorId=" + creatorId + ", orderNo=" + orderNo + ", name=" + name + ", isActive=" + isActive + ", deviceNo=" + deviceNo + ", expressNo="
				+ expressNo + ", expressCode=" + expressCode + ", orderStatus=" + orderStatus + ", schoolId=" + schoolId + ", confirmTime=" + confirmTime + ", customerId="
				+ customerId + ", originalAmount=" + originalAmount + ", orderAmount=" + orderAmount + ", deliveryTime=" + deliveryTime + ", creatorInfo=" + creatorInfo
				+ ", customerInfo=" + customerInfo + ", customerMemo=" + customerMemo + ", paymentReceipt=" + paymentReceipt + ", roleType=" + roleType + ", customerName="
				+ customerName + ", customerMobile=" + customerMobile + ", deliveryAddress=" + deliveryAddress + ", deliveryMemo=" + deliveryMemo + ", cancelReason="
				+ cancelReason + "]";
	}

}
