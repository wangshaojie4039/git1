package cn.imexue.ec.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密解密
 * 
 * @author Li Jianfeng
 * @version 1.2
 */
public class EncryptUtil {

	// AES
	// AES/CBC/NoPadding (128)
	// AES/CBC/PKCS5Padding (128)
	// AES/ECB/NoPadding (128)
	// AES/ECB/PKCS5Padding (128)
	// DES/CBC/NoPadding (56)
	// DES/CBC/PKCS5Padding (56)
	// DES/ECB/NoPadding (56)
	// DES/ECB/PKCS5Padding (56)
	// DESede/CBC/NoPadding (168)
	// DESede/CBC/PKCS5Padding (168)
	// DESede/ECB/NoPadding (168)
	// DESede/ECB/PKCS5Padding (168)
	// RSA/ECB/PKCS1Padding (1024, 2048)
	// RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
	// RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
	private static final String CIPHER_TRANSFERMATION = "AES/ECB/PKCS5Padding";

	/**
	 * aes 加密
	 * 
	 * @param key
	 *            密钥，16位
	 * @param text
	 *            明文
	 * @return 密文
	 */
	public static String stringAESEncrytion(String key, String text) {
		try {
			if (key == null || key.length() != 16) {
				return null;
			} else {
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),
						"AES");
				Cipher cipher = Cipher.getInstance(CIPHER_TRANSFERMATION);
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

				byte[] bytOut = cipher.doFinal(text.getBytes());
				return byte2Hex(bytOut);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * des解密
	 * 
	 * @param key
	 *            密钥，16位
	 * @param sSrc
	 *            密文
	 * @return 密文
	 */
	public static String stringAESDecrytion(String key, String sSrc) {
		try {
			if (key == null || key.length() != 16) {
				return null;
			} else {
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),
						"AES");
				Cipher aesDeCipher = Cipher.getInstance(CIPHER_TRANSFERMATION);
				aesDeCipher.init(Cipher.DECRYPT_MODE, skeySpec);
				byte[] original = aesDeCipher.doFinal(hex2byte(sSrc));
				return new String(original);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	private static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 != 0) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}

	private static String byte2Hex(byte[] bytOut) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < bytOut.length; n++) {
			stmp = (java.lang.Integer.toHexString(bytOut[n] & 0XFF));
			if (stmp.length() == 1) {
				hs += "0" + stmp;
			} else {
				hs += stmp;
			}
		}
		return hs;
	}

	// MD5加码。32位
	public static String MD5(String inStr) {
		try {
			MessageDigest md5 = null;
			md5 = MessageDigest.getInstance("MD5");

			char[] charArray = inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];

			byte[] md5Bytes = md5.digest(byteArray);

			StringBuffer hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}

			return hexValue.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Saas acegi使用SHA-256的哈希算法(SHA)加密 
	 * 
	 * @author Dai Dong
	 * @param password
	 * @return
	 * @return String
	 */
	public static String sha256(String password) {
//		 ShaPasswordEncoder sha = new ShaPasswordEncoder();   
//	     sha.setEncodeHashAsBase64(false);   
//	     String pwd = sha.encodePassword(password, null);   
//		return pwd;
		return null;
	}	
	
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
//		
		String text = "111111";
		String key = "1111111122222223";
		String enText = EncryptUtil.stringAESEncrytion(key, text);
		System.out.println(enText);

		String text2 = EncryptUtil.stringAESDecrytion(key, enText);
		System.out.println(text2);
		
		System.out.println(sha256("111111"));
		
	}
}
