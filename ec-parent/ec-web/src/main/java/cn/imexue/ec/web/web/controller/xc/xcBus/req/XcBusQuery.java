package cn.imexue.ec.web.web.controller.xc.xcBus.req;

import cn.imexue.ec.common.model.page.PageQuery;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.XcBus.req.XcBusQuery.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月7日</br>
 * 功能说明： 校车查询 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcBusQuery extends PageQuery {

	/**
	 * 车牌号
	 */
	private String	plateNumber;

	/**
	 * 状态
	 */
	private Byte	status;

	public String getPlateNumber() {

		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {

		this.plateNumber = plateNumber;
	}

	public Byte getStatus() {

		return status;
	}

	public void setStatus(Byte status) {

		this.status = status;
	}

}
