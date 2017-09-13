package cn.imexue.ec.common.model.vo;

import java.util.List;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.SchoolChildRideVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年8月1日<br/>
 * 功能说明： 学校乘车幼儿信息 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class SchoolChildRideVo {

	/**
	 * 乘车幼儿班级
	 */
	private String					className;

	/**
	 * 幼儿班级Id
	 */
	private Long					classId;

	/**
	 * 乘车幼儿
	 */
	private List<ChildRideExtVo>	childRideExtVo;

	public String getClassName() {

		return className;
	}

	public void setClassName(String className) {

		this.className = className;
	}

	public Long getClassId() {

		return classId;
	}

	public void setClassId(Long classId) {

		this.classId = classId;
	}

	public List<ChildRideExtVo> getChildRideExtVo() {

		return childRideExtVo;
	}

	public void setChildRideExtVo(List<ChildRideExtVo> childRideExtVo) {

		this.childRideExtVo = childRideExtVo;
	}

}
