package cn.imexue.ec.web.web.controller.xc.xcDriver.req;

import cn.imexue.ec.common.model.page.PageQuery;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.req.XcDriverQuery.java</br>
 * 初始作者： wangshaojie</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 司机分页搜索类 <br/> 
 * 
 * =================================================<br/> 
 * 修改记录：<br/> 
 * 修改作者        日期       修改内容<br/> 
 * 
 * 
 * ================================================<br/> 
 *  Copyright (橘子股份-幼儿事业部) 2017-2018.All rights reserved.<br/> 
 */
public class XcDriverQuery extends PageQuery  {

	private String driverName;
	
	private String driverTel;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}


	
	
}
