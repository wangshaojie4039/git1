package cn.imexue.ec.web.model.mongo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("ChildAttendanceDto")
public class ChildAttendanceDto {

	private Long classId;
	
	private String className;
	
	private String childName;
	
	private String logo;
	
	private Date inTime;
	
	private Byte inStatus;
	
	private String inReason;
	
	private Byte outStatus;
	
	private String outReason;
	
	private Date outTime;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Byte getInStatus() {
		return inStatus;
	}

	public void setInStatus(Byte inStatus) {
		this.inStatus = inStatus;
	}

	public String getInReason() {
		return inReason;
	}

	public void setInReason(String inReason) {
		this.inReason = inReason;
	}

	public Byte getOutStatus() {
		return outStatus;
	}

	public void setOutStatus(Byte outStatus) {
		this.outStatus = outStatus;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
}
