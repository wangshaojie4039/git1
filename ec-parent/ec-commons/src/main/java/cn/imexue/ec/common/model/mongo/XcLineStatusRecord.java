package cn.imexue.ec.common.model.mongo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * 
 * XcLineStatusRecord.java
 * 
 * <p>
 * 线路运行状态记录
 * </p>
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since 2017年7月17日
 * @author zhoudw
 * @version 1.0
 */
public class XcLineStatusRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2801962438324963408L;
	@Id
	private ObjectId id;
	private Long schoolId;
	private Long driverId;
	private Long busId;
	private Long lineId;
	private int isEnd;// 0-已发车；1-已结束
	private Date beginTime;// 行程开始时间
	private Date endTime;// 行程结束时间
	private Date createTime;

	public ObjectId getId() {
		return id;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public Long getCarId() {
		return busId;
	}

	public Long getLineId() {
		return lineId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public void setCarId(Long carId) {
		this.busId = carId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
}
