package cn.imexue.ec.common.model.vo;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcBusItemVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月19日<br/>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcBusItemVo {

	/**
	 * busId
	 */
	private Long	id;

	/**
	 * 车辆型号
	 */
	private String	model;

	/**
	 * 车牌号
	 */
	private String	plateNumber;

	/**
	 * 核载人数
	 */
	private Integer	nuclearSeating;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getModel() {

		return model;
	}

	public void setModel(String model) {

		this.model = model;
	}

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

}
