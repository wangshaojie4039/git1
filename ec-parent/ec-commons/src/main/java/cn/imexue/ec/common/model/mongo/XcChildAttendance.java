package cn.imexue.ec.common.model.mongo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * 
 * SchoolCarAttendanceRecord.java
 * 
 * <p>
 * 幼儿校车考勤记录
 * </p>
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since 2017年7月17日
 * @author zhoudw
 * @version 1.0
 */
public class XcChildAttendance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5215573931035489444L;

	@Id
	private ObjectId id;

	private Long schoolId;
	private String cardNo;
	private Long classId;
	
	private String className;
	private Long userId;
	private String userName;
	private String sex;// 1男；2女；
	private String attDay;// 考勤日期：yyyy-MM-dd
	private Date swipeTime;
	private String fileName;
	private String userType;// 用户类型：C幼儿；
	private Date createTime;
	private String plateNumber;// 车牌号
	private Long lineId;
	private String lineName;
	private Long driverId;// 司机ID
	private String driverName;// 司机姓名
	private String driverTel;// 司机联系电话
	private int attType;// 0-本次不座； 1上学上车2上学下车3放学上车4放学下车
	private ObjectId lineStatusRecordId;// 线路状态ID
	private String teacherCheck;// 老师补登；值为‘TC’则代表本条记录是老师补登

	public XcChildAttendance() {
	}

	public ObjectId getId() {
		return id;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public Long getClassId() {
		return classId;
	}

	public String getClassName() {
		return className;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getSex() {
		return sex;
	}

	public Date getSwipeTime() {
		return swipeTime;
	}

	public String getFileName() {
		return fileName;
	}

	public String getUserType() {
		return userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public Long getLineId() {
		return lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public int getAttType() {
		return attType;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSwipeTime(Date swipeTime) {
		this.swipeTime = swipeTime;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public void setAttType(int attType) {
		this.attType = attType;
	}

	public Long getDriverId() {
		return driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}

	public ObjectId getLineStatusRecordId() {
		return lineStatusRecordId;
	}

	public void setLineStatusRecordId(ObjectId lineStatusRecordId) {
		this.lineStatusRecordId = lineStatusRecordId;
	}

	public String getAttDay() {
		return attDay;
	}

	public void setAttDay(String attDay) {
		this.attDay = attDay;
	}

	public String getTeacherCheck() {
		return teacherCheck;
	}

	public void setTeacherCheck(String teacherCheck) {
		this.teacherCheck = teacherCheck;
	}

}
