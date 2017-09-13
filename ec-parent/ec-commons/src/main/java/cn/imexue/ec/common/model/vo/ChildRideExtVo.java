package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.ChildRideExtVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月28日<br/>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("ChildRideExtVo")
public class ChildRideExtVo extends IdNameVO {

	private Integer	hasExit;

	private Long	xcChildID;

	public Integer getHasExit() {

		return hasExit;
	}

	public void setHasExit(Integer hasExit) {

		this.hasExit = hasExit;
	}

	public Long getXcChildID() {

		return xcChildID;
	}

	public void setXcChildID(Long xcChildID) {

		this.xcChildID = xcChildID;
	}

}
