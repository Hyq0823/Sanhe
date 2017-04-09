/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.weixin.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sh.weixin.entity.ShWeixin;
import com.thinkgem.jeesite.modules.sh.weixin.service.ShWeixinService;
import com.thinkgem.jeesite.modules.sh.weixin.utils.Constant;
import com.thinkgem.jeesite.modules.sh.weixin.utils.ResultUtils;
import com.thinkgem.jeesite.modules.sh.weixin.utils.WeixinUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.SysUtils;

/**
 * 微信模块Controller
 * @author huangyunquan
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/shWeixin")
public class ShWeixinController extends BaseController {
	
	/**注册页面*/
	private static final String REGISTER_URL ="modules/sh/weixin/register";
	/**信息完善界面*/
	private static final String MSG_COMPLETE_URL ="modules/sh/weixin/InfoComplete";
	/**个人中心*/
	private static final String USRE_CENTER="modules/sh/weixin/personalCenter";

	@Autowired
	private ShWeixinService shWeixinService;
	
	@Autowired
	private SystemService systemService; 

	/**
	 * 授权
	 * @return
	 * @author hyq
	 */
	@RequestMapping("/authorization")
	public String authorization(@RequestParam(value="callBackUrl") String callBackUrl,@RequestParam(value="code")String code,Model model) {
		try {
			//获取access_token
//			JSONObject access_data = WeixinUtils.code4accessToken(code);
//			String openid = access_data.getString("openid");
			
			String openid = "1234";
			//callBackUrl = "modules/sh/weixin/personalCenter";
			callBackUrl = "modules/sh/weixin/personalCenter";
			
			//是否鉴权
			ShWeixin shWeixin = shWeixinService.getByOpenid(openid);
			if(shWeixin == null || shWeixin.getUser() == null){
				model.addAttribute("openid", openid);
				model.addAttribute("url",callBackUrl);
				return REGISTER_URL;
			}
			model.addAttribute("userId",shWeixin.getUser().getId());
			logger.info("菜单跳转url:---->{}",callBackUrl);
			return callBackUrl;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("鉴权异常!错误：{},详细:{}",e.getMessage(),e);
			addMessage(model, "鉴权错误",e.getMessage());
			return REGISTER_URL;
		}
	}
	
	
	/**
	 * 校验手机号码是否已被注册
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/validateMobile")
	@ResponseBody
	public String validateMobile(@RequestParam(value="mobile",required=true) String mobile){
		User user = systemService.getUserByMobile(mobile);
		if(user != null){//已被注册了
			return ResultUtils.response(Constant.U_PHONE_ISREG_CODE, Constant.U_PHONE_ISREG_MSG, null);
		}
		return ResultUtils.success();
	} 
	
	@RequestMapping("/infoComplete")
	public String infoComplete(@RequestParam(value="openid",required=false) String openid,@RequestParam(value="url",required=false) String url
			,@RequestParam(value="mobile",required=false) String mobile,Model model){
		
		if(StringUtils.isEmpty(mobile)){
			return REGISTER_URL;
		}
		
		System.out.println("进入信息完善页面");
		model.addAttribute("openid",openid);
		model.addAttribute("url",url);
		model.addAttribute("mobile",mobile);
		return MSG_COMPLETE_URL;
	}
	
	/**
	 * 微信签名
	 * 
	 */
	@RequestMapping("/sign")
	public void auth(HttpServletRequest request, HttpServletResponse response) {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (WeixinUtils.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			} else {
				logger.error("校验失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
	
	
	@RequestMapping(value = "register")
	public String register(ShWeixin shWeixin, Model model) {
		model.addAttribute("shWeixin", shWeixin);
		return REGISTER_URL;
	}
	
	
	
	@RequiresPermissions("weixin:shWeixin:view")
	@RequestMapping(value = "userInfo")
	public String userInfo(ShWeixin shWeixin, Model model) {
		model.addAttribute("shWeixin", shWeixin);
		return "modules/sh/weixin/userInfo";
	}
	
	
	
	@ModelAttribute
	public ShWeixin get(@RequestParam(required=false) String id) {
		ShWeixin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shWeixinService.get(id);
		}
		if (entity == null){
			entity = new ShWeixin();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:shWeixin:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShWeixin shWeixin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShWeixin> page = shWeixinService.findPage(new Page<ShWeixin>(request, response), shWeixin); 
		model.addAttribute("page", page);
		return "sh/weixin/shWeixinList";
	}

	@RequiresPermissions("weixin:shWeixin:view")
	@RequestMapping(value = "form")
	public String form(ShWeixin shWeixin, Model model) {
		model.addAttribute("shWeixin", shWeixin);
		return "sh/weixin/shWeixinForm";
	}

	@RequestMapping(value = "save")
	public String save(ShWeixin shWeixin, Model model, RedirectAttributes redirectAttributes) {
		User user = shWeixin.getUser();
		if(user!=null){
			user.setPassword(SystemService.entryptPassword(user.getPassword()));
			user.setUserType(Constant.USER_STUDENT);
			user.setCompany(new Office("1"));
			user.setOffice(new Office("2"));
			systemService.saveUser(user);
			shWeixin.setUser(user);
		}
		shWeixinService.save(shWeixin);
		model.addAttribute("shWeixin", shWeixin);
		return USRE_CENTER;
	}
	
	@RequiresPermissions("weixin:shWeixin:edit")
	@RequestMapping(value = "delete")
	public String delete(ShWeixin shWeixin, RedirectAttributes redirectAttributes) {
		shWeixinService.delete(shWeixin);
		addMessage(redirectAttributes, "删除微信模块成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/shWeixin/?repage";
	}

}