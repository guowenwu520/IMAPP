package com.runapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 
 * <p>Title: StringUtils</p>
 * <p>Description:TODO</p>
 * <p>Company: YouJiang</p>
 * @author chaychan
 * @date 2016�?8�?13日下�?3:39:20
 * @version 1.0
 */
public class StringUtils {

	/**返回yyyy-MM-dd HH:mm:ss类型的时间字符串*/
	public static String getCurrentTimeString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//
				.format(new Date());
	}
	
	public static String getCurrentTimeNotsString() {
		return new SimpleDateFormat("yyyy.MM.dd HH:mm")//
				.format(new Date());
	}
	
	/**返回yyyy-MM-dd类型的当前时�?*/
	public static String getCurrentDateString() {
		return new SimpleDateFormat("yyyy-MM-dd")//
				.format(new Date());
	}
	/**
	 * MD5加密
	 * @param str
	 * @return
	 */
	public static String resutlMD5(String str) {
		String resutl = "";
		try {
			MessageDigest instance = MessageDigest.getInstance("MD5");// 获取MD5算法对象
			byte[] digest = instance.digest(str.getBytes());// 对字符串加密,返回字节数组
			StringBuffer strBuffer = new StringBuffer();
			for (byte b : digest) {
				int i = b & 0xff;
				String hexString = Integer.toHexString(i);// 将整数转�?16进制
				if (hexString.length() < 2) {
					hexString = "0" + hexString;// 如果�?1位的�?,�?0
				}
				strBuffer.append(hexString);
			}
			resutl = strBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resutl;
	}
	/**
	 * 对字符进行排�?
	 * @param str
	 * @return
	 */
	public static String sortingStr(String str){
		char[] arr=str.toCharArray();
		Arrays.sort(arr);
		StringBuffer buf=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			buf.append(arr[i]);
		}
		return buf.toString();
	}
	
	/**产生新的文件�?*/
	public static String createNewFileName(String oldName){
		
		String ext = "";
        
		if (oldName.lastIndexOf(".") != -1) {
            //有后�?�?
            ext = oldName.substring(oldName.lastIndexOf("."));
            oldName = oldName.substring(0,oldName.lastIndexOf("."));//得到文件�?
        }
        
        String newName = UidUtils.generateUid() + ext;
		
        return newName;
	}
	
	public static boolean isEmpty(String str){
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}
	
	/**
	 * 获取弹框的脚本字符串
	 * @param redirectUrl 重定向的url
	 * @return
	 */
	public static String getScriptString(String tip,String redirectUrl){
		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">");
		if(!StringUtils.isEmpty(tip)){
			sb.append("alert('" + tip +"');");
		}
		if(!StringUtils.isEmpty(redirectUrl)){
			sb.append("location.href = '" + redirectUrl + "';");
		}
		sb.append("</script>");
		return sb.toString();
	}
}
