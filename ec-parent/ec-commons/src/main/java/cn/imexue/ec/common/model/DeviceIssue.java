package cn.imexue.ec.common.model;

import java.util.Date;

public class DeviceIssue {

	private Long	id;

	private String	schoolId;

	private String	content;

	private Byte	isSolve;

	private Date	solveTime;

	private String	solveUserRole;

	private Long	solveUserId;

	private Date	createTime;

	private Long	creatorId;

	private String	creatorName;

	private String	creatorMobile;

	// 以下是自定义字段
	// 学校名字
	private String	schoolName;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(String schoolId) {

		this.schoolId = schoolId;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public Byte getIsSolve() {

		return isSolve;
	}

	public void setIsSolve(Byte isSolve) {

		this.isSolve = isSolve;
	}

	public Date getSolveTime() {

		return solveTime;
	}

	public void setSolveTime(Date solveTime) {

		this.solveTime = solveTime;
	}

	public String getSolveUserRole() {

		return solveUserRole;
	}

	public void setSolveUserRole(String solveUserRole) {

		this.solveUserRole = solveUserRole;
	}

	public Long getSolveUserId() {

		return solveUserId;
	}

	public void setSolveUserId(Long solveUserId) {

		this.solveUserId = solveUserId;
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	public Long getCreatorId() {

		return creatorId;
	}

	public void setCreatorId(Long creatorId) {

		this.creatorId = creatorId;
	}

	public String getCreatorName() {

		return creatorName;
	}

	public void setCreatorName(String creatorName) {

		this.creatorName = creatorName;
	}

	public String getCreatorMobile() {

		return creatorMobile;
	}

	public void setCreatorMobile(String creatorMobile) {

		this.creatorMobile = creatorMobile;
	}

	public String getSchoolName() {

		return schoolName;
	}

	public void setSchoolName(String schoolName) {

		this.schoolName = schoolName;
	}

	@Override
	public String toString() {

		return "DeviceIssue [id=" + id + ", schoolId=" + schoolId + ", content=" + content + ", isSolve=" + isSolve + ", solveTime=" + solveTime + ", solveUserRole="
				+ solveUserRole + ", solveUserId=" + solveUserId + ", createTime=" + createTime + ", creatorId=" + creatorId + ", creatorName=" + creatorName + ", creatorMobile="
				+ creatorMobile + ", schoolName=" + schoolName + "]";
	}

}
