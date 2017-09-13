package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.TeacherInfoVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月1日</br>
 * 功能说明： 老师基本信息 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("TeacherInfoVo")
public class TeacherInfoVo {

	private Long	id;
	private String	name;

	private String	mobile;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

}
