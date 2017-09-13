package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcBusVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月7日</br>
 * 功能说明： 校车查询列表VO <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcBusVo")
public class XcBusVo {

	private Long	id;

	/**
	 * 学校ID
	 */
	private Long	schoolId;
	/**
	 * 线路id
	 */
	private Long	lineId;

	/**
	 * 线路名称
	 */
	private String	lineName;

	/**
	 * 车辆型号
	 */
	private String	model;

	/**
	 * 车牌号
	 */
	private String	plateNumber;

	/**
	 * 核载人数
	 */
	private Integer	nuclearSeating;

	/**
	 * 考勤机编号
	 */
	private String	attendanceDeviceNo;

	/**
	 * 考勤机名称
	 */
	private String	attendanceDeviceName;

	/**
	 * 视频设备编号
	 */
	private String	cameraDeviceNo;

	private Integer	versionNo;

	/**
	 * 状态：0-使用中；1-停用
	 */
	@JsonIgnore
	private Byte	status;

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

	public String getCameraDeviceNo() {

		return cameraDeviceNo;
	}

	public void setCameraDeviceNo(String cameraDeviceNo) {

		this.cameraDeviceNo = cameraDeviceNo;
	}

	public String getAttendanceDeviceName() {

		return attendanceDeviceName;
	}

	public void setAttendanceDeviceName(String attendanceDeviceName) {

		this.attendanceDeviceName = attendanceDeviceName;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
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
