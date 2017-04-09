/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.apply.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApply;
import com.thinkgem.jeesite.modules.sh.apply.service.ShApplyService;

/**
 * 报名表Controller
 * @author huangyunquan
 * @version 2017-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/shApply")
public class ShApplyController extends BaseController {

	@Autowired
	private ShApplyService shApplyService;
	
	/**
	 * 我的报名
	 * @return
	 */
	@RequestMapping("/applys")
	@ResponseBody
	public List<ShApply> getApplyListByUserId(@RequestParam("userId") String userId){
		List<ShApply> list = shApplyService.getListByUserId(userId);
		return list;
	}
	
	
	
	
	@ModelAttribute
	public ShApply get(@RequestParam(required=false) String id) {
		ShApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shApplyService.get(id);
		}
		if (entity == null){
			entity = new ShApply();
		}
		return entity;
	}
	
	@RequiresPermissions("a:shApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShApply shApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShApply> page = shApplyService.findPage(new Page<ShApply>(request, response), shApply); 
		model.addAttribute("page", page);
		return "sh/a/shApplyList";
	}

	@RequiresPermissions("a:shApply:view")
	@RequestMapping(value = "form")
	public String form(ShApply shApply, Model model) {
		model.addAttribute("shApply", shApply);
		return "sh/a/shApplyForm";
	}

	@RequiresPermissions("a:shApply:edit")
	@RequestMapping(value = "save")
	public String save(ShApply shApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shApply)){
			return form(shApply, model);
		}
		shApplyService.save(shApply);
		addMessage(redirectAttributes, "保存报名表成功");
		return "redirect:"+Global.getAdminPath()+"/a/shApply/?repage";
	}
	
	@RequiresPermissions("a:shApply:edit")
	@RequestMapping(value = "delete")
	public String delete(ShApply shApply, RedirectAttributes redirectAttributes) {
		shApplyService.delete(shApply);
		addMessage(redirectAttributes, "删除报名表成功");
		return "redirect:"+Global.getAdminPath()+"/a/shApply/?repage";
	}

}