package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.XcLine;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcLineVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明： 校车线路列表vo <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcLineVo")
public class XcLineVo extends XcLine {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= -1327436724390241021L;

	/**
	 * 车牌号
	 */
	private String				plateNumber;

	/**
	 * 核载人数
	 */
	private Integer				nuclearSeating;

	/**
	 * 幼儿数目
	 */
	private Integer				childCount;

	public String getPlateNumber() {

		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {

		this.plateNumber = plateNumber;
	}

	public Integer getNuclearSeating() {

		return nuclearSeating;
	}

	public void setNuclearSeating(Integer nuclearSeating) {

		this.nuclearSeating = nuclearSeating;
	}

	public Integer getChildCount() {

		return childCount;
	}

	public void setChildCount(Integer childCount) {

		this.childCount = childCount;
	}

}
