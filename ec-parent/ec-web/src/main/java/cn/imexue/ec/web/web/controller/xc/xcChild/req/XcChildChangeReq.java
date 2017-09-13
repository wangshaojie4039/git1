package cn.imexue.ec.web.web.controller.xc.xcChild.req;

import javax.validation.constraints.NotNull;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (橘子股份-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcChildChangeReq {

	@NotNull
	private Long	id;
	@NotNull
	private Integer	versionNo;

	private Long	schoolId;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

}
