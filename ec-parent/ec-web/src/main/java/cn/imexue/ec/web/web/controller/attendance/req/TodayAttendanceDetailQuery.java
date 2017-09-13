package cn.imexue.ec.web.web.controller.attendance.req;

import cn.imexue.ec.common.model.page.PageQuery;


public class TodayAttendanceDetailQuery extends PageQuery{

	private String attendDiv;

	private String inOrOut;
	
	private Long classId;
	
	public String getAttendDiv() {
		return attendDiv;
	}

	public void setAttendDiv(String attendDiv) {
		this.attendDiv = attendDiv;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}
	
}

