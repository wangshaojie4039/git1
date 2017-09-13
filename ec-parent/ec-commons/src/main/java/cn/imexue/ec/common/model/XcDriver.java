package cn.imexue.ec.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.imexue.ec.common.model.common.DataEntity;

/**
 * 文件名称： cn.imexue.ec.common.model.XcDriver.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月6日</br>
 * 功能说明： 司机 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcDriver extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 4357124080680592108L;

	private Long				schoolId;

	private String				driverName;

	private String				idNo;

	private String				licenseNo;

	private String				driverTel;

	private String				attendanceCardNo;

	@JsonIgnore
	private Byte				isDelete;

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public String getDriverName() {

		return driverName;
	}

	public void setDriverName(String driverName) {

		this.driverName = driverName;
	}

	public String getIdNo() {

		return idNo;
	}

	public void setIdNo(String idNo) {

		this.idNo = idNo;
	}

	public String getLicenseNo() {

		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {

		this.licenseNo = licenseNo;
	}

	public String getDriverTel() {

		return driverTel;
	}

	public void setDriverTel(String driverTel) {

		this.driverTel = driverTel;
	}

	public String getAttendanceCardNo() {

		return attendanceCardNo;
	}

	public void setAttendanceCardNo(String attendanceCardNo) {

		this.attendanceCardNo = attendanceCardNo;
	}

	public Byte getIsDelete() {

		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {

		this.isDelete = isDelete;
	}

}
