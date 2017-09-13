package cn.imexue.ec.common.model.vo;

/**
 * 幼儿考勤详细信息用VO
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月24日
 * @author zhengxy
 * @version 1.0
 */
public class ChildAttendanceDetailVO {
	
	private Long childId;
	
	private String childName;
	
	private String childLogo;
	
	private String parentMobile;
	
	private String status;
	
	private String leaveType;
	
	private String leaveName;

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getChildLogo() {
		return childLogo;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setChildLogo(String childLogo) {
		this.childLogo = childLogo;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	
}
