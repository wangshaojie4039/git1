package cn.imexue.ec.common.model.vo;

public class ChildAttendanceDayVO {
	
	private Long attendanceId;
	
	private Long childId;
	
    private String childName;
    
    private String attStatus;

    private String time;
    
    private String leaveType;

    private String reason;
    
    private String logoUrl;
    
    private String attendanceDay;

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

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

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getAttendanceDay() {
		return attendanceDay;
	}

	public void setAttendanceDay(String attendanceDay) {
		this.attendanceDay = attendanceDay;
	}
}
