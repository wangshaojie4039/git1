package cn.imexue.ec.common.model.vo;

import java.util.List;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.ChildRideVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月24日<br/>
 * 功能说明： 乘车幼儿Vo<br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */

public class ChildRideVo {

	/**
	 * 乘车幼儿总数
	 */
	private Integer					childTotal;

	/**
	 * 乘车班级
	 */
	private List<ClassRideVoItem>	xcClassList;

	public Integer getChildTotal() {

		return childTotal;
	}

	public void setChildTotal(Integer childTotal) {

		this.childTotal = childTotal;
	}

	public List<ClassRideVoItem> getXcClassList() {

		return xcClassList;
	}

	public void setXcClassList(List<ClassRideVoItem> xcClassList) {

		this.xcClassList = xcClassList;
	}

}
