/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sh.weixin.entity.ShWeixin;
import com.thinkgem.jeesite.modules.sh.weixin.service.ShWeixinService;

/**
 * 微信模块Controller
 * @author huangyunquan
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/shWeixin")
public class ShWeixinController extends BaseController {

	@Autowired
	private ShWeixinService shWeixinService;
	
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

	@RequiresPermissions("weixin:shWeixin:edit")
	@RequestMapping(value = "save")
	public String save(ShWeixin shWeixin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shWeixin)){
			return form(shWeixin, model);
		}
		shWeixinService.save(shWeixin);
		addMessage(redirectAttributes, "保存微信模块成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/shWeixin/?repage";
	}
	
	@RequiresPermissions("weixin:shWeixin:edit")
	@RequestMapping(value = "delete")
	public String delete(ShWeixin shWeixin, RedirectAttributes redirectAttributes) {
		shWeixinService.delete(shWeixin);
		addMessage(redirectAttributes, "删除微信模块成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/shWeixin/?repage";
	}

}