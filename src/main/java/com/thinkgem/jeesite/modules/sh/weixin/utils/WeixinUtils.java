package com.thinkgem.jeesite.modules.sh.weixin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.module.sitemesh.taglib.page.ApplyDecoratorTag;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApplyInfo;
import com.thinkgem.jeesite.modules.sys.utils.ConstUtils;
import com.thinkgem.jeesite.modules.sys.utils.SysUtils;



public class WeixinUtils {
	
	
	private static Logger logger = LoggerFactory.getLogger(WeixinUtils.class);
	private static final String ACCESS_TOKEN = "access_token";
	private static final String ACCESS_TIME = "access_time";
	private static final String TICKET = "ticket";
	private static final String TICKET_TIME = "ticket_time";
	// 获取access_token的请求链接
	private static  String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=@1&secret=@2";
	
	// 获取ticket的请求链接1
	private static String getTicketTokenUrl1 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
	// 获取ticket的请求链接2
	private static String getTicketTokenUrl2 = "&type=jsapi";
	
	// access_token缓存map
	private static Map<String, String> accessTokenMap = new HashMap<String, String>();
	
	// ticket缓存map
	private static Map<String, String> ticketMap = new HashMap<String, String>();
	/**
	 *  签名的随机字符串
	 */
	public static final String NONCESTR = "Wm3WZYTPz0wzccnW";
	
	/**
	 * 微信发送模板消息接口调用地址
	 */
	private static final String SHARE_TEMP_PATH = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

	/**
	 * 分享的资源的打开连接2
	 */
	private static final String SHARE_TEMP_OPEN_PATH2 = "&userOpenId=";
	
	
	
	
	
	//==========================================================================================
	
	public static final String AUTHTOKEN = Global.getConfig("weixin.authToken");//接入token
	public static final String MENU_REGSTER = "register";//注册
	public static final String MENU_BIND_LOGIN = "bindLogin";//绑定登录
	public static final String MENU_USER_INFO = "userInfo";//用户信息
	public static final String MENU_ORDER_HISTORY = "orderHistory";//订单历史
	public static final String MENU_ORDER_CREATE = "orderCreate";//创建订单
	
	
	
	/**
	 * 签名校验
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		 String[] arr = new String[] { AUTHTOKEN, timestamp, nonce };
	        Arrays.sort(arr);//排序
	        StringBuilder content = new StringBuilder();
	        for (int i = 0; i < arr.length; i++) {
	            content.append(arr[i]);
	        }
	        String tmpStr = SHA1(content.toString());//SHA1签名校验
	        return tmpStr != null ? tmpStr.equalsIgnoreCase(signature) : false;
	}
	
	
	
	/**
	 * code换取access_token
	 * @author hyq
	 * @param code
	 *  " https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code ";
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject code4accessToken(String code) throws Exception {
		StringBuffer sbUrl = new StringBuffer();
		sbUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
			 .append("?")
			 .append("appid=").append(SysUtils.getPropertyByName("weixin.appid"))
			 .append("&secret=").append(SysUtils.getPropertyByName("weixin.secret"))
			 .append("&code=").append(code)
			 .append("&grant_type=").append("authorization_code")
			 .append("&t=").append(new Date().getTime());
		String receive = HttpUtils.doGet(sbUrl.toString());
		JSONObject data = new JSONObject(receive);
		if(data.has("errcode")){//TODO页面刷新导致无效的 COde重新拉取code
			logger.error("微信code异常: {},{}",data.get("errcode"),data.get("errmsg"));
			throw new Exception(ConstUtils.WEXIN_MSG_INVALID);
		}
		return data;
	}
	
	
	/**
	 * 拉取用户信息
	 * @author hyq
	 * @param openid
	 * @param access_token2
	 *  https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject access_token4UserInfo(String openid,String access_token) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("https://api.weixin.qq.com/sns/userinfo?access_token=").append(access_token)
		  .append("&openid=").append(openid)
		  .append("&lang=").append("zh_CN");
		String result = HttpUtils.doGet(sb.toString());
		JSONObject data = new JSONObject(result);
		if(data.has("errcode")){//TODO openid无效
			logger.error("openId无效: {},{}",data.get("errcode"),data.get("errmsg"));
			throw new Exception(ConstUtils.WEXIN_MSG_OPENID);
		}
		return data;
	}
	//==========================================================================================
	/**
	 * 刷新access_token
	 */
	//在测试服务器上运行时放开
	private static void refreshAccessToken() {
		try {
			System.out.println("更新access_token");
			System.out.println(getAccessTokenUrl);
			getAccessTokenUrl = getAccessTokenUrl.replace("@1",SysUtils.getPropertyByName("weixin.appid"))
					.replace("@2", SysUtils.getPropertyByName("weixin.secret"));
			
			System.out.println(getAccessTokenUrl);
			System.out.println(getAccessTokenUrl);
			
			String resultStr = HttpUtils.doGet(getAccessTokenUrl);
			System.out.println(resultStr);
			JSONObject result = new JSONObject(resultStr);
			Long time = new Date().getTime() / 1000;
//	        {ACCESS_TOKEN:"fXI0rGc61jveI2e2AwIc4vn5Q199svLDEXZCU84AFxiOLQImxlK-o9mke05eoDgj9vQKjZ0qKehB6ejH_1UZjFXcaGmPdM9d6B4ychk5W1QBJPjAAAMUA","expires_in":7200}

			accessTokenMap.put(ACCESS_TOKEN, result.get(ACCESS_TOKEN) + "");
			accessTokenMap.put(ACCESS_TIME, time+"");
			System.out.println("更新access_token成功");
		} catch (Exception e) {
			System.err.println("更新access_token失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 刷新ticket
	 */
	private static void refreshTicket() {
		try {
			System.out.println("更新ticket");
			String resultStr = HttpUtils.doGet(getTicketTokenUrl1+getAccessToken()+getTicketTokenUrl2);
			System.out.println(resultStr);
			JSONObject result = new JSONObject(resultStr);
			Long time = new Date().getTime() / 1000;
//	    	{"errcode":0,"errmsg":"ok","ticket":"kgt8ON7yVITDhtdwci0qeRixP_HCupM_uEuGSX6CI8tamATB8Ql1ty2C2U5G2Qt61CnJXm_GizxORJ7o7IfWRQ","expires_in":7200}
			
			ticketMap.put(TICKET, result.get(TICKET) + "");
			ticketMap.put(TICKET_TIME, time+"");
			System.out.println("更新ticket成功");
		} catch (Exception e) {
			System.err.println("更新ticket失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	private static String getAccessToken() {
		String accessToken = accessTokenMap.get(ACCESS_TOKEN);
		// 如果没有缓存access_token，重新获取
		if(accessToken == null || "".equals(accessToken)) {
			refreshAccessToken();
			accessToken = accessTokenMap.get(ACCESS_TOKEN);
		} else {
			Long time = Long.valueOf(accessTokenMap.get(ACCESS_TIME));
			Long nowTime = new Date().getTime() / 1000;
			// 如果access_token缓存时长超过两个小时，重新获取
			if(nowTime - time > 7200) {
				refreshAccessToken();
				accessToken = accessTokenMap.get(ACCESS_TOKEN);
			}
		}
		
		return accessToken;
	}
	
	/**
	 * 获取ticket
	 * @return
	 */
	private static String getTicket() {
		String ticket = ticketMap.get(TICKET);
		// 如果没有缓存ticket，重新获取
		if(ticket == null || "".equals(ticket)) {
			refreshTicket();
			ticket = ticketMap.get(TICKET);
		} else {
			Long time = Long.valueOf(ticketMap.get(TICKET_TIME));
			Long nowTime = new Date().getTime() / 1000;
			// 如果access_token缓存时长超过两个小时，重新获取
			if(nowTime - time > 7200) {
				refreshTicket();
				ticket = ticketMap.get(TICKET);
			}
		}
		
		return ticket;
	}
	
	/**
	 * 获取签名
	 * @param timestamp 时间戳
	 * @param url 链接
	 * @param jsapi_ticket ticket
	 * @param noncestr 随机串
	 * @return
	 */
	public static String getSHA1(String timestamp, String url, String noncestr) {
		String signStr = "jsapi_ticket=" + getTicket() +
    			"&noncestr=" + noncestr +
    			"&timestamp=" + timestamp +
    			"&url="+url;
    	return SHA1(signStr);
	}

	private static String SHA1(String decript) {
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
	
	/**
	 * 获取js access_token
	 * @return
	 */
	public static String getJsToken() {
		return getAccessToken();
	}
	
	/**
	 * 微信推送分享消息
	 * <p>
	 * 
	 * 资料名称：name
	 * 资料简介：describe
	 * remark
	 * @return
	 */
	public static String msgShare(String openId,ShApplyInfo info) {
		String accessToken = getJsToken();
		// 拼接数据
		JSONObject data = new JSONObject(); 
		data.put("template_id",SysUtils.getPropertyByName("weixin.share_temp_id"));//发送的模版id
        data.put("touser", openId);//用户的openid
        
        JSONObject content = new JSONObject();
        	JSONObject first = new JSONObject();
        	first.put("value", "您好,您收到一条新发布的报名通知");

        	//报名标题
        	JSONObject applyNameJson = new JSONObject();
        	applyNameJson.put("value", info.getName());
        	
        	//报名开始时间
        	JSONObject startTimeJson = new JSONObject();
        	startTimeJson.put("value", DateUtils.formatDate(info.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        	
        	//报名结束时间
        	JSONObject endTimeJson = new JSONObject();
        	endTimeJson.put("value", DateUtils.formatDate(info.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
        	
        	//报名详情
        	JSONObject descJson = new JSONObject();
        	descJson.put("value", info.getDescription());
        	
        	//报名备注
        	JSONObject remarkJson = new JSONObject();
        	remarkJson.put("value", info.getRemarks());
        	
        	
        content.put("name", applyNameJson);
        content.put("startTime", startTimeJson);
        content.put("endTime", endTimeJson);
        content.put("description", descJson); 
        content.put("remarks", remarkJson);
        data.put("data", content);
      
        try {
			String resultStr = HttpUtils.doPost(SHARE_TEMP_PATH + accessToken, data);
			JSONObject result = new JSONObject(resultStr);
			if(result.getInt("errcode") == 0) { // 发送成功
				return "0";
			} else { // 发送失败
				return String.valueOf(result.getInt("errcode"));
			}
        } catch (Exception e) {
			e.printStackTrace();
			return "Send Msg Error";
		}
      
	}
}
