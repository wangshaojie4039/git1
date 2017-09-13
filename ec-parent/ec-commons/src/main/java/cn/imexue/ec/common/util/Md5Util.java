package cn.imexue.ec.common.util;

import java.security.MessageDigest;
/**
 * 
 * Md5Util.java 
 * 
 * <p>
 *  MD5工具类
 * </p>
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年2月10日
 * @author zhoudw
 * @version 1.0
 */
public class Md5Util {
	
	public static String encrypt(String str){
		if(StringUtil.isEmpty(str)){
			return null;
		}
		return getMessageDigest(str.getBytes());
	}
	
	public static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(Md5Util.getMessageDigest("23423rsqk6sce1490167231c6719f15090b4e547282e412f85d2755".getBytes()));
	}
}
