package com.thinkgem.jeesite.modules.sh.weixin.utils;

import org.activiti.engine.impl.util.json.JSONObject;

/**
 * 封装返回结果的util
 * @author hyq
 *
 */
public class ResultUtils {

	
	/**
	 * 响应
	 * @param errorcode
	 * @param msg
	 * @param data
	 * @return
	 */
	public static String response(String errorcode,String msg,Object data){
		JSONObject result = new JSONObject();
		result.put(Constant.ERROR_CODE,errorcode);
		result.put(Constant.ERROR_MSG,msg);
		result.put(Constant.DATA, data);
		return result.toString();
	}
	
	/**
	 * 处理成功！
	 */
	public static String success(){
		JSONObject result = new JSONObject();
		result.put(Constant.ERROR_CODE,Constant.SUCCESS_CODE);
		result.put(Constant.ERROR_MSG,Constant.SUCCESS_MSG);
		result.put(Constant.DATA, "");
		return result.toString();
	}
}
