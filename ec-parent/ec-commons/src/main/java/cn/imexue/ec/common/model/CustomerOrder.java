package cn.imexue.ec.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerOrder {
    private Long id;

    private String orderNo;

    private Long customerId;

    private String customerInfo;

    private Byte orderStatus;

    private BigDecimal originalAmount;

    private BigDecimal orderAmount;

    private Date changePriceTime;

    private String changePriceUserRole;

    private Long changePriceUserId;

    private String paymentReceipt;

    private String customerMemo;

    private String financeMemo;

    private String deliveryMemo;

    private String cancelReason;

    private Date deliveryTime;

    private String deliveryAddress;

    private Date confirmTime;

    private String expressCode;

    private String expressNo;

    private String creatorInfo;

    private Date payTime;

    private Date createTime;

    private String creatorRole;

    private Long creatorId;

    private Date updateTime;

    private String updaterRole;

    private Long updaterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getChangePriceTime() {
        return changePriceTime;
    }

    public void setChangePriceTime(Date changePriceTime) {
        this.changePriceTime = changePriceTime;
    }

    public String getChangePriceUserRole() {
        return changePriceUserRole;
    }

    public void setChangePriceUserRole(String changePriceUserRole) {
        this.changePriceUserRole = changePriceUserRole;
    }

    public Long getChangePriceUserId() {
        return changePriceUserId;
    }

    public void setChangePriceUserId(Long changePriceUserId) {
        this.changePriceUserId = changePriceUserId;
    }

    public String getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(String paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }

    public String getCustomerMemo() {
        return customerMemo;
    }

    public void setCustomerMemo(String customerMemo) {
        this.customerMemo = customerMemo;
    }

    public String getFinanceMemo() {
        return financeMemo;
    }

    public void setFinanceMemo(String financeMemo) {
        this.financeMemo = financeMemo;
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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(String creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", orderNo=" + orderNo
				+ ", customerId=" + customerId + ", customerInfo="
				+ customerInfo + ", orderStatus=" + orderStatus
				+ ", originalAmount=" + originalAmount + ", orderAmount="
				+ orderAmount + ", changePriceTime=" + changePriceTime
				+ ", changePriceUserRole=" + changePriceUserRole
				+ ", changePriceUserId=" + changePriceUserId
				+ ", paymentReceipt=" + paymentReceipt + ", customerMemo="
				+ customerMemo + ", financeMemo=" + financeMemo
				+ ", deliveryMemo=" + deliveryMemo + ", cancelReason="
				+ cancelReason + ", deliveryTime=" + deliveryTime
				+ ", deliveryAddress=" + deliveryAddress + ", confirmTime="
				+ confirmTime + ", expressCode=" + expressCode + ", expressNo="
				+ expressNo + ", creatorInfo=" + creatorInfo + ", payTime="
				+ payTime + ", createTime=" + createTime + ", creatorRole="
				+ creatorRole + ", creatorId=" + creatorId + ", updateTime="
				+ updateTime + ", updaterRole=" + updaterRole + ", updaterId="
				+ updaterId + "]";
	}
    
    
}