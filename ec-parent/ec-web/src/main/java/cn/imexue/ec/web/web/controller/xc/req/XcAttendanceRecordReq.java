package cn.imexue.ec.web.web.controller.xc.req;

import java.util.Date;

public class XcAttendanceRecordReq {

	private String childName;

	private String plateNumber;

	private Long classId;

	private Date swipeStart;

	private Date swipeEnd;

	private Long lineId;

	private Integer attType;

	private Integer pageNo;

	private Integer pageSize = 20;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Date getSwipeStart() {
		return swipeStart;
	}

	public void setSwipeStart(Date swipeStart) {
		this.swipeStart = swipeStart;
	}

	public Date getSwipeEnd() {
		return swipeEnd;
	}

	public void setSwipeEnd(Date swipeEnd) {
		this.swipeEnd = swipeEnd;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Integer getAttType() {
		return attType;
	}

	public void setAttType(Integer attType) {
		this.attType = attType;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
