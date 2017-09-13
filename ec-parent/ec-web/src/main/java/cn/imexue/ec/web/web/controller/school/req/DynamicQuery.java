package cn.imexue.ec.web.web.controller.school.req;

import java.util.Date;

import cn.imexue.ec.common.model.page.PageQuery;

public class DynamicQuery extends PageQuery{

	private Long  classId;
	 
	private String  userInfo;
	 
	private String content;
	 
	private Date  startTime;
	 
	private Date  endTime;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	

}
