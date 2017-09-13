package cn.imexue.ec.web.web.controller.xc.xcDriver.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.req.XcDriverReq.java</br>
 * 初始作者： wangshaojie</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (橘子股份-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcDriverReq {

	private Long id;

	@NotNull(message = "司机姓名不能为空")
	@Length(max = 20)
	private String driverName;

	@NotNull(message = "身份证号码不能为空")
	@Pattern(regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)", message = "身份证号码格式错误")
	private String idNo;

	@NotNull(message = "驾驶证编号不能为空")
	private String licenseNo;

	@NotNull(message = "司机电话号码不能为空")
	@Length(max = 13)
	private String driverTel;

	@NotNull(message = "考勤卡号不能为空")
	@Pattern(regexp = "\\d{10}", message = "考勤卡号长度必须为10位数字")
	private String attendanceCardNo;

	@Min(0)
	private Integer	versionNo;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
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

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

}
