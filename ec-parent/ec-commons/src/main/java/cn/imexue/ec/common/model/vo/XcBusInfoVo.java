package cn.imexue.ec.common.model.vo;

import cn.imexue.ec.common.model.XcBus;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcBusInfoVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月25日<br/>
 * 功能说明： 校车详情 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcBusInfoVo {

	private XcBus	xcBus;

	private Long	lineId;

	public XcBus getXcBus() {

		return xcBus;
	}

	public void setXcBus(XcBus xcBus) {

		this.xcBus = xcBus;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

}
