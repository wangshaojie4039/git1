package cn.imexue.ec.common.model.mongo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * 
 * XcChildCardRecord.java
 * 
 * <p>
 * 校车幼儿刷卡记录表-mongo
 * </p>
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since 2017年6月29日
 * @author zhoudw
 * @version 1.0
 */
public class XcChildCardRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2771286229611245468L;
	@Id
	private ObjectId id;
	private Long schoolId;
	private Date swipeTime;
	private String cardNo;
	private Long userId;
	private String userType;
	private String fileName;
	private Date createTime;
	private Long lineId;
	private Long busId;
	private Long driverId;
	private ObjectId lineStatusRecordId;// 线路状态ID

	public Long getSchoolId() {
		return schoolId;
	}

	public Date getSwipeTime() {
		return swipeTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserType() {
		return userType;
	}

	public String getFileName() {
		return fileName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setSwipeTime(Date swipeTime) {
		this.swipeTime = swipeTime;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Long getBusId() {
		return busId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public ObjectId getLineStatusRecordId() {
		return lineStatusRecordId;
	}

	public void setLineStatusRecordId(ObjectId lineStatusRecordId) {
		this.lineStatusRecordId = lineStatusRecordId;
	}

}
