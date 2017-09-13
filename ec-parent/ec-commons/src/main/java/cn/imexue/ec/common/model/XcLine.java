package cn.imexue.ec.common.model;

import cn.imexue.ec.common.model.common.DataEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文件名称： cn.imexue.ec.common.model.XcLine.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月6日</br>
 * 功能说明： 线路<br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcLine extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 108702528680648528L;

	private Long schoolId;

	private String lineName;

	@JsonIgnore
	private Long teacherId;

	@JsonIgnore
	private Byte isDelete;

	private Long driverId;
	
	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public String getLineName() {

		return lineName;
	}

	public void setLineName(String lineName) {

		this.lineName = lineName;
	}

	public Long getTeacherId() {

		return teacherId;
	}

	public void setTeacherId(Long teacherId) {

		this.teacherId = teacherId;
	}

	public Byte getIsDelete() {

		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {

		this.isDelete = isDelete;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

}
