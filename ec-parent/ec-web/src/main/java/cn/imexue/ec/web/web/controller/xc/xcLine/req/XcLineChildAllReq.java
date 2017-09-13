package cn.imexue.ec.web.web.controller.xc.xcLine.req;

import java.io.Serializable;
import java.util.List;

import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildSaveReq;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineChildAllReq.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月29日<br/>
 * 功能说明： 乘车线路添加幼儿 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcLineChildAllReq implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 3263006175724875271L;

	List<XcChildSaveReq>		XcLineChildReqList;

	public List<XcChildSaveReq> getXcLineChildReqList() {

		return XcLineChildReqList;
	}

	public void setXcLineChildReqList(List<XcChildSaveReq> xcLineChildReqList) {

		XcLineChildReqList = xcLineChildReqList;
	}

}
