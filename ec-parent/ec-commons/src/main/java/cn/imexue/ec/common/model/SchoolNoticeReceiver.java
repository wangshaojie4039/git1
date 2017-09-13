package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

public class SchoolNoticeReceiver extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1397387164549523444L;

	private Long				id;

	private Long				schoolId;

	private Long				schoolNoticeId;

	private String				receiverRole;

	private Long				receiverId;

	private String				receiverName;

	private Byte				isRead;

	private Date				createTime;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public Long getSchoolNoticeId() {

		return schoolNoticeId;
	}

	public void setSchoolNoticeId(Long schoolNoticeId) {

		this.schoolNoticeId = schoolNoticeId;
	}

	public String getReceiverRole() {

		return receiverRole;
	}

	public void setReceiverRole(String receiverRole) {

		this.receiverRole = receiverRole;
	}

	public Long getReceiverId() {

		return receiverId;
	}

	public void setReceiverId(Long receiverId) {

		this.receiverId = receiverId;
	}

	public String getReceiverName() {

		return receiverName;
	}

	public void setReceiverName(String receiverName) {

		this.receiverName = receiverName;
	}

	public Byte getIsRead() {

		return isRead;
	}

	public void setIsRead(Byte isRead) {

		this.isRead = isRead;
	}

	@Override
	public Date getCreateTime() {

		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}
}
