package cn.imexue.ec.common.model.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.XcLine;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcLineBusVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月27日<br/>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcLineBusVo")
public class XcLineBusVo extends XcLine implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= -435333247634667913L;

	private Integer				nuclearSeating;

	public Integer getNuclearSeating() {

		return nuclearSeating;
	}

	public void setNuclearSeating(Integer nuclearSeating) {

		this.nuclearSeating = nuclearSeating;
	}

}
