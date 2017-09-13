package cn.imexue.ec.common.model;

import java.util.Date;

public class ChildAttendance {
	private Long id;

    private Long schoolId;

    private Long classId;

    private Long childId;

    private String childName;

    private Date attendanceDate;

    private Byte inStatus;

    private Date inTime;

    private String inLeaveType;

    private String inLeaveReason;

    private String inImageUrl;

    private Byte outStatus;

    private Date outTime;

    private String outLeaveType;

    private String outLeaveReason;

    private String outImageUrl;

    private Date createTime;
    
    /** 状态 */
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
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

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Byte getInStatus() {
		return inStatus;
	}

	public void setInStatus(Byte inStatus) {
		this.inStatus = inStatus;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getInLeaveType() {
		return inLeaveType;
	}

	public void setInLeaveType(String inLeaveType) {
		this.inLeaveType = inLeaveType;
	}

	public String getInLeaveReason() {
		return inLeaveReason;
	}

	public void setInLeaveReason(String inLeaveReason) {
		this.inLeaveReason = inLeaveReason;
	}

	public String getInImageUrl() {
		return inImageUrl;
	}

	public void setInImageUrl(String inImageUrl) {
		this.inImageUrl = inImageUrl;
	}

	public Byte getOutStatus() {
		return outStatus;
	}

	public void setOutStatus(Byte outStatus) {
		this.outStatus = outStatus;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getOutLeaveType() {
		return outLeaveType;
	}

	public void setOutLeaveType(String outLeaveType) {
		this.outLeaveType = outLeaveType;
	}

	public String getOutLeaveReason() {
		return outLeaveReason;
	}

	public void setOutLeaveReason(String outLeaveReason) {
		this.outLeaveReason = outLeaveReason;
	}

	public String getOutImageUrl() {
		return outImageUrl;
	}

	public void setOutImageUrl(String outImageUrl) {
		this.outImageUrl = outImageUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}