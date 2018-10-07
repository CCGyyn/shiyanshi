package com.zeng.ezsh.wy.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class StringMd5Util {
	/**
	 * @author qwc
	 * 2017年7月14日上午1:10:56
	 * @param inStr
	 * @return String
	 */
	public static String MD5Digest(String inStr) {
	 	MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
        for (int i = 0; i < charArray.length; i++)  
        byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
	        int val = ((int) md5Bytes[i]) & 0xff;  
	        if (val < 16)  
	        hexValue.append("0");  
	        hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString(); 	
	}
	
	/**
	 * @author qwc
	 * 2017年8月4日上午1:09:42
	 * @param str utf8编码的字符串
	 * @param key
	 * @return 加密结果
	 * @throws Exception byte[]
	 * 使用DES对字符串加密
	 */
	public static byte[] desEncrypt(String str, String key) throws Exception { 
		if (str == null || key == null) return null; 
		Cipher cipher = Cipher.getInstance("DES"); 
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "DES")); 
		byte[] bytes = cipher.doFinal(str.getBytes("utf-8")); 
		return  bytes;
	} 
   
	   
	  /**
	   * @author qwc
	   * 2017年8月4日上午1:05:27
	   * @param bytes
	   * @param key 密钥
	   * @return 解密结果
	   * @throws Exception String
	   * 使用DES对数据解密
	   */
	public static String desDecrypt(byte[] bytes, String key) throws Exception { 
		if (bytes == null || key == null) return null; 
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); 
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "DES")); 
		bytes = cipher.doFinal(bytes);
		return new String(bytes, "utf-8"); 
	} 
}
