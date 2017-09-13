package cn.imexue.ec.common.model.bo.sms;

/**
 * 短信记录
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年2月28日
 * @author lijianfeng
 * @version 1.0
 */
public class SmsMessageBO {
	private String mobile;
	
	private String msg;
	
	private int templateType;
	
	public SmsMessageBO() {
	}

	public SmsMessageBO(String mobile, String msg, int templateType) {
		super();
		this.mobile = mobile;
		this.msg = msg;
		this.templateType = templateType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getTemplateType() {
		return templateType;
	}

	public void setTemplateType(int templateType) {
		this.templateType = templateType;
	}
}
