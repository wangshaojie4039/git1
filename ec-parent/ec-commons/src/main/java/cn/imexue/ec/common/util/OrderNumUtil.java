package cn.imexue.ec.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * YsCloudResource.java
 * <p>订单号生成<p>
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 * @date 创建时间：2017年2月23日 上午11:32:55
 * @since 2017/01/04
 * @author 崔业新
 * @version 1.0
 */
public class OrderNumUtil {
	/**
	 * 
	  * <p>根据设备序列号、通道号、通道名获取相关群信息。</p>
	  * 
	  * @param	star  开头
	  * @param	dateStart	日期的起始位置(含)
	  * @param	dateEnd		日期的终止位置（不含）
	  * @param	num		随机数的位数
	  * @return 群的相关信息
	  * @throws Exception		抛出异常
	 */
	public static String orderProduct(String star,int dateStart,int dateEnd,int num){
		StringBuffer orderProducts=new StringBuffer();
		orderProducts.append(star);
		
		String strDate =getStringToday();
		String strDates= strDate.substring(dateStart, dateEnd);
		orderProducts.append(strDates);
		orderProducts.append(StringUtil.getRandomDigit(num));
		return orderProducts.toString();
	}
	 public static  String getStringToday() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	}
	
}
