package cn.imexue.ec.common.model;

import cn.imexue.ec.common.model.common.DataEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文件名称： cn.imexue.ec.common.model.XcBus.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月6日</br>
 * 功能说明： 校车 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcBus extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 7096947015772300711L;

	private Long				schoolId;
	private Long				lineId;

	private String				model;

	private String				plateNumber;

	private Integer				nuclearSeating;

	private String				attendanceDeviceNo;

	@JsonIgnore
	private String				cameraDeviceNo;

	private Byte				status;

	@JsonIgnore
	private Byte				isDelete;

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public String getModel() {

		return model;
	}

	public void setModel(String model) {

		this.model = model;
	}

	public String getPlateNumber() {

		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {

		this.plateNumber = plateNumber;
	}

	public Integer getNuclearSeating() {

		return nuclearSeating;
	}

	public void setNuclearSeating(Integer nuclearSeating) {

		this.nuclearSeating = nuclearSeating;
	}

	public String getAttendanceDeviceNo() {

		return attendanceDeviceNo;
	}

	public void setAttendanceDeviceNo(String attendanceDeviceNo) {

		this.attendanceDeviceNo = attendanceDeviceNo;
	}

	public String getCameraDeviceNo() {

		return cameraDeviceNo;
	}

	public void setCameraDeviceNo(String cameraDeviceNo) {

		this.cameraDeviceNo = cameraDeviceNo;
	}

	public Byte getStatus() {

		return status;
	}

	public void setStatus(Byte status) {

		this.status = status;
	}

	public Byte getIsDelete() {

		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {

		this.isDelete = isDelete;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
