package cn.imexue.ec.web.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AttendanceSchoolModel {

	private Long schoolId;
	
	private String date;

	private Long classId;
	
	private String attendDiv;//1：已打卡 0:未打卡
	
	@NotNull
	@Pattern(regexp="(in|out){1}")
	private String inOutDiv;//in：出勤 out:退勤
	
	@NotNull
	@Pattern(regexp="(BL|SL|OTHER){1}")
	private String leaveType;//事假/病假/其他
	
	@Length(max=20)
	private String leaveReason;
	
	@NotNull
	private Long childId;
	
	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getAttendDiv() {
		return attendDiv;
	}

	public void setAttendDiv(String attendDiv) {
		this.attendDiv = attendDiv;
	}

	public String getInOutDiv() {
		return inOutDiv;
	}

	public void setInOutDiv(String inOutDiv) {
		this.inOutDiv = inOutDiv;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}
	
	public void setInOrOut(String inOrOut){
		this.inOutDiv = inOrOut;
	}
}
