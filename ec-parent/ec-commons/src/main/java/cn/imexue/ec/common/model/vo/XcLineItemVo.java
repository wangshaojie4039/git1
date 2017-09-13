package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.XcDriver;
import cn.imexue.ec.common.model.XcLine;
import cn.imexue.ec.common.model.mongo.XcLineStatusRecord;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcLineItemVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明： 校车线路详情vo <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcLineItemVo")
public class XcLineItemVo extends XcLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7813855513550855400L;
	/**
	 * 线路id
	 */
	private Long		id;

	/**
	 * 校车详情
	 */
	private XcBusItemVo	xcBusItemVo;

	private XcDriver driver;
	
	private Integer totalRide;
	
	private Integer ride;
	
	private XcLineStatusRecord lineStatus;
	
	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public XcBusItemVo getXcBusItemVo() {

		return xcBusItemVo;
	}

	public void setXcBusItemVo(XcBusItemVo xcBusItemVo) {

		this.xcBusItemVo = xcBusItemVo;
	}

	public XcDriver getDriver() {
		return driver;
	}

	public void setDriver(XcDriver driver) {
		this.driver = driver;
	}

	public XcLineStatusRecord getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(XcLineStatusRecord lineStatus) {
		this.lineStatus = lineStatus;
	}

	public Integer getTotalRide() {
		return totalRide;
	}

	public void setTotalRide(Integer totalRide) {
		this.totalRide = totalRide;
	}

	public Integer getRide() {
		return ride;
	}

	public void setRide(Integer ride) {
		this.ride = ride;
	}

}
