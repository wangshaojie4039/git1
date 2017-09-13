package cn.imexue.ec.web.web.controller.xc.xcLine.req;

import javax.validation.constraints.NotNull;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineChangeReq.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明： 校车线路请求 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcLineChangeReq {

	@NotNull
	private Long	id;
	@NotNull
	private Integer	versionNo;

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

}
