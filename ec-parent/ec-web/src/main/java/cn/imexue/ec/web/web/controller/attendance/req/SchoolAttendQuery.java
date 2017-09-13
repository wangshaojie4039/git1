package cn.imexue.ec.web.web.controller.attendance.req;

import java.util.Date;

import javax.validation.constraints.NotNull;

import cn.imexue.ec.common.model.page.PageQuery;

public class SchoolAttendQuery extends PageQuery{

	private Long classId;
	
	@NotNull
	private Date start;
	
	@NotNull
	private Date end;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
