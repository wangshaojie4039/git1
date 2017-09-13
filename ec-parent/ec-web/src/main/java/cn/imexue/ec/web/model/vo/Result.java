package cn.imexue.ec.web.model.vo;

import java.io.Serializable;

/**
 * 文件名称： cn.imexue.ec.web.model.vo.Result.java<br/>
 * 初始作者： 崔业新<br/>
 * 创建日期： 2017年4月14日<br/>
 * 功能说明： ajax返回结果接收类 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2012-2017.All rights reserved.<br/>
 */
public class Result implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= -5452923141105546555L;
	private boolean				isSuccess			= true;
	private String				errCode;
	private String				errMsg;
	private Object				data;

	public Result() {

	}

	public Result(boolean isSuccess, String errCode, String errMsg, Object data) {

		this.isSuccess = isSuccess;
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.data = data;
	}

	public Boolean getIsSuccess() {

		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {

		this.isSuccess = isSuccess;
	}

	public String getErrCode() {

		return errCode;
	}

	public void setErrCode(String errCode) {

		this.errCode = errCode;

	}

	public String getErrMsg() {

		return errMsg;
	}

	public void setErrMsg(String errMsg) {

		this.errMsg = errMsg;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {

		this.data = data;
	}

	@Override
	public String toString() {

		return "Result [data=" + data + ", errCode=" + errCode + ", errMsg="
				+ errMsg + ", isSuccess=" + isSuccess + "]";
	}

}
