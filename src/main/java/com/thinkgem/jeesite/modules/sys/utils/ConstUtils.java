package com.thinkgem.jeesite.modules.sys.utils;


public class ConstUtils {
	
	public static final String UTF_8 = "UTF-8";
	
	public static final String CONTENT_TYPE = "application/json";

	public static final String CONTENT_TYPE_UTF8 = "application/json; charset=UTF-8";
	

	
	//=====================微信接口消息类型=====================================
	/**
	 * - 需要鉴权
	 */
	public static final String WeiXIN_CODE_AUTH = "0030";
	public static final String WEXIN_MSG_AUTH = "请先鉴权";
	
	/**
	 * - 登录或注册放行
	 */
	public static final String WeiXIN_CODE_RELEASE = "0031";
	public static final String WEXIN_MSG_RELEASE = "登录或注册放行！";
	
	/**
     * - 微信无效的code
     */
    public static final String WeiXIN_CODE_INVALID = "0032";
    public static final String WEXIN_MSG_INVALID = "无效的微信code！";
    
    /**
     * - 微信无效的openid
     */
    public static final String WeiXIN_CODE_OPENID = "0033";
    public static final String WEXIN_MSG_OPENID = "无效的微信openId！";
	
	//=====================微信接口消息类型end=====================================
	
	
}
