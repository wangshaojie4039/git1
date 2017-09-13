package cn.imexue.ec.web.web.controller.xc.xcBus.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.XcBus.req.XcBusReq.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月7日</br>
 * 功能说明： 新增修改校车req <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcBusReq {

	private Long	id;
	private Long	schoolId;
	/**
	 * 线路id
	 */
	@NotNull(message = "线路id不能为空")
	private Long	lineId;

	/**
	 * 线路名称
	 */
	private String	lineName;
	/**
	 * 车辆型号
	 */
	@NotNull(message = "车辆型号不能为空")
	private String	model;
	/**
	 * 车牌
	 */
	@NotNull
	@Length(max = 10, message = "车牌号不能超过10位")
	private String	plateNumber;
	/**
	 * 核载人数
	 */
	@NotNull(message = "核载人数不能为空")
	@Max(value = 60, message = "核载人数不能超过60")
	private Integer	nuclearSeating;
	/**
	 * 考勤机编号
	 */
	@NotNull(message = "考勤机编号不能为空")
	private String	attendanceDeviceNo;
	/**
	 * 状态：0-使用中；1-停用
	 */
	private Byte	status;

	/**
	 * 视频设备编号
	 */
	private String	cameraDeviceNo;

	/**
	 * 版本号
	 */
	private Integer	versionNo;

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

	public Byte getStatus() {

		return status;
	}

	public void setStatus(Byte status) {

		this.status = status;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

	public String getCameraDeviceNo() {

		return cameraDeviceNo;
	}

	public void setCameraDeviceNo(String cameraDeviceNo) {

		this.cameraDeviceNo = cameraDeviceNo;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

	public String getLineName() {

		return lineName;
	}

	public void setLineName(String lineName) {

		this.lineName = lineName;
	}

}
