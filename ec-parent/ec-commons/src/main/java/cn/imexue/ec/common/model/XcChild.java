package cn.imexue.ec.common.model;

import cn.imexue.ec.common.model.common.DataEntity;

/**
 * 文件名称： cn.imexue.ec.common.model.XcChild.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月6日</br>
 * 功能说明： 幼儿<br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcChild extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1987926280131022736L;

	private Long				schoolId;

	private Long				lineId;

	private Long				childId;

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

	public Long getChildId() {

		return childId;
	}

	public void setChildId(Long childId) {

		this.childId = childId;
	}

}
