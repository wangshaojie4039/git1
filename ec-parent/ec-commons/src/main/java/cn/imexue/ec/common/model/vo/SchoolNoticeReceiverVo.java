package cn.imexue.ec.common.model.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.SchoolNoticeReceiver;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.SchoolNoticeReceiverVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月1日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */

@Alias("SchoolNoticeReceiverVo")
public class SchoolNoticeReceiverVo extends SchoolNoticeReceiver implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 4352782485140754578L;
	private String				mobile;

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

}
