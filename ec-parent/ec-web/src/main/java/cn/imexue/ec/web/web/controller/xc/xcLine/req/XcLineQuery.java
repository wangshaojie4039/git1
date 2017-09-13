package cn.imexue.ec.web.web.controller.xc.xcLine.req;

import cn.imexue.ec.common.model.page.PageQuery;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineQuery.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明： 校车线路查询列表 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcLineQuery extends PageQuery {

	private String	lineName;

	public String getLineName() {

		return lineName;
	}

	public void setLineName(String lineName) {

		this.lineName = lineName;
	}

}
