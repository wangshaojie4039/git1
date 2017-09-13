package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.ClassInfoVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月3日</br>
 * 功能说明： 班级基本信息 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("ClassInfoVo")
public class ClassInfoVo {

	private Long	id;

	private String	name;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

}
