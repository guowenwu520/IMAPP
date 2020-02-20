package com.runapp.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Description: 通用的json返回内容工具�?
 * Company: youjiang
 * @author Kwum
 * @date 2016�?12�?19�? 下午5:20:09
 * @version 1.0
 */

public class NormalReturnUtil {
	
	/**
	 * 状�?�码
	 */
	private static final String JSONCODE = "code";
	
	/**
	 * 消息内容
	 */
	private static final String JSONMSG = "msg";
	
	/**
	 * 错误�?
	 * 1000	没有权限
	 * 1001	该管理员已启�?
	 * 1002	该管理员已弃�?	
	 */
	private static final String JSONERRCODE = "err_code";
	
	/**
	 * 系统错误信息内容
	 */
	private static final String JSONSYSTEMERRMSG = "system boom";
	
	/**
	 * 数据�?
	 */
	private static final String JSONDATA = "data";

	/**
	 * 通用的json返回内容（三参数：操作失误）
	 * @param code 状�?�码:1成功�?0参数错误�?-1系统异常�?-999未登�?
	 * @param msg 消息内容 
	 * @param err_code 错误码：第一个参数为0时则返回的json中无此内�?
	 * @return json数据，例：{"code":0,"msg":"password is wrong","err_code":1002}
	 * @author kwum
	 */
	public static String normalReturn(int code, String msg, int err_code){
		JSONObject json = new JSONObject();
		json.put(JSONCODE, code);
		json.put(JSONMSG, msg);
		if(code == 0){
			json.put(JSONERRCODE, err_code);
		}
		return json.toString();
	}
	
	/**
	 * 通用的json返回内容（两参数�?
	 * @param code 状�?�码:1成功�?0参数错误�?-1系统异常�?-999未登�?
	 * @param msg 消息内容 
	 * @return json数据，例：{"code":-999,"msg":"login first please"}
	 * @author kwum
	 */
	public static String normalReturn(int code, String msg){
		JSONObject json = new JSONObject();
		json.put(JSONCODE, code);
		json.put(JSONMSG, msg);
		return json.toString();
	}
	
	/**
	 * 通用的json返回内容（一参数：系统错误）
	 * @param code 状�?�码:1成功�?0参数错误�?-1系统异常�?-999未登�?
	 * @return json数据，例：{"code":-1,"msg":"system boom"}
	 * @author kwum
	 */
	public static String normalReturn(int code){
		JSONObject json = new JSONObject();
		json.put(JSONCODE, code);
		if(code == -1){
			json.put(JSONMSG, JSONSYSTEMERRMSG);
		}
		return json.toString();
	}
	
	/**
	 * 通用的json返回内容（三参数：成功）
	 * @param code 状�?�码:1成功�?0参数错误�?-1系统异常�?-999未登�?
	 * @param msg 消息内容 
	 * @return json数据，例：{"code":1,"msg":"login success","data":"awesome"}
	 * @author kwum
	 */
	public static String normalReturn(int code, String msg, JSONObject jsonObject){
		JSONObject json = new JSONObject();
		json.put(JSONCODE, code);
		if(code == 1){
			json.put(JSONMSG, msg);
			json.put(JSONDATA, jsonObject);
		}
		return json.toString();
	}
	
	/**
	 * 通用的json返回内容（三参数：成功）
	 * @param code 状�?�码:1成功�?0参数错误�?-1系统异常�?-999未登�?
	 * @param msg 消息内容 
	 * @return json数据，例：{"code":1,"msg":"login success","data":"awesome"}
	 * @author kwum
	 */
	public static String normalReturn(int code, String msg, JSONArray jsonArray){
		JSONObject json = new JSONObject();
		json.put(JSONCODE, code);
		if(code == 1){
			json.put(JSONMSG, msg);
			json.put(JSONDATA, jsonArray);
		}
		return json.toString();
	}
	
	/**
	 * 通用的json返回内容（三参数：成功）
	 * @param code 状�?�码:1成功�?0参数错误�?-1系统异常�?-999未登�?
	 * @param msg 消息内容 
	 * @return json数据，例：{"code":1,"msg":"login success","data":"awesome"}
	 * @author kwum
	 */
	public static String normalReturn(int code, String msg, String data){
		JSONObject json = new JSONObject();
		json.put(JSONCODE, code);
		if(code == 1){
			json.put(JSONMSG, msg);
			json.put(JSONDATA, data);
		}
		return json.toString();
	}
}
