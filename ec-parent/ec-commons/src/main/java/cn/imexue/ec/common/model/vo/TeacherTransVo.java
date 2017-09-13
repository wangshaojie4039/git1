package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.UserInfo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月4日</br>
 * 功能说明： 老师传输对象 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("TeacherTransVo")
public class TeacherTransVo {

	private String	uid;

	private String	password;

	public String getUid() {

		return uid;
	}

	public void setUid(String uid) {

		this.uid = uid;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

}
