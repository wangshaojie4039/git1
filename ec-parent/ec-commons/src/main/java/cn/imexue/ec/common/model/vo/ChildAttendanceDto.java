package cn.imexue.ec.common.model.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("ChildAttendanceDto")
public class ChildAttendanceDto {

	private Long classId;
	
	private Long childId;
	
	private String childName;
	
	private String logo;
	
	private Date inTime;
	
	private Integer inStatus;
	
	private String inReason;
	
	private String inReasonType;
	
	private Integer outStatus;
	
	private String outReason;
	
	private String outReasonType;
	
	private Date outTime;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
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

	public String getInReason() {
		return inReason;
	}

	public void setInReason(String inReason) {
		this.inReason = inReason;
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

	public Integer getInStatus() {
		return inStatus;
	}

	public void setInStatus(Integer inStatus) {
		this.inStatus = inStatus;
	}

	public Integer getOutStatus() {
		return outStatus;
	}

	public void setOutStatus(Integer outStatus) {
		this.outStatus = outStatus;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getInReasonType() {
		return inReasonType;
	}

	public void setInReasonType(String inReasonType) {
		this.inReasonType = inReasonType;
	}

	public String getOutReasonType() {
		return outReasonType;
	}

	public void setOutReasonType(String outReasonType) {
		this.outReasonType = outReasonType;
	}
	
}
