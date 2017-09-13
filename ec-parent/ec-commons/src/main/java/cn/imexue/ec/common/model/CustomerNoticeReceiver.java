package cn.imexue.ec.common.model;

import java.util.Date;

public class CustomerNoticeReceiver {

	private Long	id;

	private Long	noticeId;

	private Long	customerId;

	private Byte	isRead;

	private Date	createTime;

	private String	creatorRole;

	private Long	creatorId;

	// 自添加字段
	private String	mobile;

	private String	customerName;

	private String	title;

	private Date	noticeCreateTime;

	private Byte	noticeLevel;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Long getNoticeId() {

		return noticeId;
	}

	public void setNoticeId(Long noticeId) {

		this.noticeId = noticeId;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public Byte getIsRead() {

		return isRead;
	}

	public void setIsRead(Byte isRead) {

		this.isRead = isRead;
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

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public Date getNoticeCreateTime() {

		return noticeCreateTime;
	}

	public void setNoticeCreateTime(Date noticeCreateTime) {

		this.noticeCreateTime = noticeCreateTime;
	}

	public Byte getNoticeLevel() {

		return noticeLevel;
	}

	public void setNoticeLevel(Byte noticeLevel) {

		this.noticeLevel = noticeLevel;
	}

	@Override
	public String toString() {

		return "CustomerNoticeReceiver [id=" + id + ", noticeId=" + noticeId + ", customerId=" + customerId + ", isRead=" + isRead + ", createTime=" + createTime
				+ ", creatorRole=" + creatorRole + ", creatorId=" + creatorId + ", mobile=" + mobile + ", customerName=" + customerName + ", title=" + title
				+ ", noticeCreateTime=" + noticeCreateTime + ", noticeLevel=" + noticeLevel + "]";
	}

}
