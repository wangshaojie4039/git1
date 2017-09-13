package cn.imexue.ec.web.util;

import java.math.BigDecimal;

/**
 * web应用相关的参数
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月13日
 * @author lijianfeng
 * @version 1.0
 */
public class WebConstants {

	// ///////////用户公共session//////////////////
	public static final String		SESSION_USER_VERIFY_CODE	= "vcode";							// 验证码

	public static final String		DEVICE_STATUS_NORMAL		= "正常";							// 正常
	public static final String		DEVICE_STATUS_ERROR			= "异常";							// 异常

	public static final String		CUSTOMER_ROLE				= "1";								// 代理商
	public static final String		INDENTOR_ROLE				= "2";								// 订货商

	public static final Byte		WITHDRAW_STATUS_0			= 0;								// 待审核
	public static final Byte		WITHDRAW_STATUS_1			= 1;								// 已审核

	public static final BigDecimal	WITHDRAW_TO_0				= new BigDecimal(0.00);			// 提现清0

	/** 是否删除 **/
	public static final Byte		IS_DELETE_0					= 0;								// 区代
	public static final Byte		IS_DELETE_1					= 1;								// 区代
	/** 允许登录 **/
	public static final Byte		IS_ALLOWLOGIN_0				= 0;								// 允许

}
