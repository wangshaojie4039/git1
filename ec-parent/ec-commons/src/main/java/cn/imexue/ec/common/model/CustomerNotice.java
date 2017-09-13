package cn.imexue.ec.common.model;

import java.util.Date;

public class CustomerNotice {

	private Long	id;

	private String	title;

	private String	content;

	private Byte	noticeLevel;

	private Integer	receiverCount;

	private Byte	sendSms;

	private Date	createTime;

	private String	creatorRole;

	private Long	creatorId;

	private Date	updateTime;

	private String	updaterRole;

	private Long	updaterId;
	// 以下为自添加字段
	private String	announcePersion;	// 发布者

	private Integer	readNum;			// 已读数目

	private String	creatorName;		// 创建者

	private String	deptName;			// 创建者部门

	private Long	noticeReceiverId;	// 阅读情况表

	private Byte	isRead;				// 是否阅读

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public Byte getNoticeLevel() {

		return noticeLevel;
	}

	public void setNoticeLevel(Byte noticeLevel) {

		this.noticeLevel = noticeLevel;
	}

	public Integer getReceiverCount() {

		return receiverCount;
	}

	public void setReceiverCount(Integer receiverCount) {

		this.receiverCount = receiverCount;
	}

	public Byte getSendSms() {

		return sendSms;
	}

	public void setSendSms(Byte sendSms) {

		this.sendSms = sendSms;
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

	public String getAnnouncePersion() {

		return announcePersion;
	}

	public void setAnnouncePersion(String announcePersion) {

		this.announcePersion = announcePersion;
	}

	public Integer getReadNum() {

		return readNum;
	}

	public void setReadNum(Integer readNum) {

		this.readNum = readNum;
	}

	public String getCreatorName() {

		return creatorName;
	}

	public void setCreatorName(String creatorName) {

		this.creatorName = creatorName;
	}

	public String getDeptName() {

		return deptName;
	}

	public void setDeptName(String deptName) {

		this.deptName = deptName;
	}

	public Long getNoticeReceiverId() {

		return noticeReceiverId;
	}

	public void setNoticeReceiverId(Long noticeReceiverId) {

		this.noticeReceiverId = noticeReceiverId;
	}

	@Override
	public String toString() {

		return "CustomerNotice [id=" + id + ", title=" + title + ", content=" + content + ", noticeLevel=" + noticeLevel + ", receiverCount=" + receiverCount + ", sendSms="
				+ sendSms + ", createTime=" + createTime + ", creatorRole=" + creatorRole + ", creatorId=" + creatorId + ", updateTime=" + updateTime + ", updaterRole="
				+ updaterRole + ", updaterId=" + updaterId + ", announcePersion=" + announcePersion + ", readNum=" + readNum + ", creatorName=" + creatorName + ", deptName="
				+ deptName + ", noticeReceiverId=" + noticeReceiverId + ", isRead=" + isRead + "]";
	}

}
