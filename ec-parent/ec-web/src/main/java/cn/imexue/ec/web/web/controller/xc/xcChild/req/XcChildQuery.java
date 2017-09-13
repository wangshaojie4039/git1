package cn.imexue.ec.web.web.controller.xc.xcChild.req;

import cn.imexue.ec.common.model.page.PageQuery;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildQuery.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 校车幼儿查询 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcChildQuery extends PageQuery {

	private String	name;

	private Long	classId;
	private Long	lineId;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Long getClassId() {

		return classId;
	}

	public void setClassId(Long classId) {

		this.classId = classId;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

}
