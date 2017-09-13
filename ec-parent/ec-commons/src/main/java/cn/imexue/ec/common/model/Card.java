package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

public class Card extends DataEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 8823954084749587272L;

	private String				cardNo;

	private Long				schoolId;

	private Long				customerId;

	private String				userType;

	private Long				userId;

	private String				orderNo;

	private Date				confirmTime;

	private String				holderRoleCode;

	private String				holderRoleName;

	private Date				bindTime;

	private Byte				isActive;

	public String getCardNo() {

		return cardNo;
	}

	public void setCardNo(String cardNo) {

		this.cardNo = cardNo;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public String getUserType() {

		return userType;
	}

	public void setUserType(String userType) {

		this.userType = userType;
	}

	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
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

	public String getHolderRoleCode() {

		return holderRoleCode;
	}

	public void setHolderRoleCode(String holderRoleCode) {

		this.holderRoleCode = holderRoleCode;
	}

	public String getHolderRoleName() {

		return holderRoleName;
	}

	public void setHolderRoleName(String holderRoleName) {

		this.holderRoleName = holderRoleName;
	}

	public Date getBindTime() {

		return bindTime;
	}

	public void setBindTime(Date bindTime) {

		this.bindTime = bindTime;
	}

	public Byte getIsActive() {

		return isActive;
	}

	public void setIsActive(Byte isActive) {

		this.isActive = isActive;
	}

	@Override
	public String toString() {

		return "Card [cardNo=" + cardNo + ", schoolId=" + schoolId + ", customerId=" + customerId + ", userType=" + userType + ", userId=" + userId + ", orderNo=" + orderNo
				+ ", confirmTime=" + confirmTime + ", holderRoleCode=" + holderRoleCode + ", holderRoleName=" + holderRoleName + ", bindTime=" + bindTime + ", isActive="
				+ isActive + "]";
	}

}
