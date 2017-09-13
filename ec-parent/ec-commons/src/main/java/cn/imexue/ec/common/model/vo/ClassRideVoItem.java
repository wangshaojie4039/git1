package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.ClassRideVoItem.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月24日<br/>
 * 功能说明： 乘车幼儿班级情况 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("ClassRideVoItem")
public class ClassRideVoItem {

	/**
	 * 班级幼儿数目
	 */
	private Integer	classChildTotal;

	/**
	 * 乘车幼儿班级
	 */
	private String	className;

	/**
	 * 幼儿班级Id
	 */
	private Long	classId;

	public Integer getClassChildTotal() {

		return classChildTotal;
	}

	public void setClassChildTotal(Integer classChildTotal) {

		this.classChildTotal = classChildTotal;
	}

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

}
