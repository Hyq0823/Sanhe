/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.apply.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApplyInfo;
import com.thinkgem.jeesite.modules.sh.apply.service.ShApplyInfoService;

/**
 * 报名信息Controller
 * @author huangyunquan
 * @version 2017-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/apply/shApplyInfo")
public class ShApplyInfoController extends BaseController {

	@Autowired
	private ShApplyInfoService shApplyInfoService;
	
	@ModelAttribute
	public ShApplyInfo get(@RequestParam(required=false) String id) {
		ShApplyInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shApplyInfoService.get(id);
		}
		if (entity == null){
			entity = new ShApplyInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("apply:shApplyInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShApplyInfo shApplyInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ShApplyInfo> list = shApplyInfoService.findList(shApplyInfo); 
		model.addAttribute("list", list);
		return "modules/sh/apply/shApplyInfoList";
	}

	@RequiresPermissions("apply:shApplyInfo:view")
	@RequestMapping(value = "form")
	public String form(ShApplyInfo shApplyInfo, Model model) {
		if (shApplyInfo.getParent()!=null && StringUtils.isNotBlank(shApplyInfo.getParent().getId())){
			shApplyInfo.setParent(shApplyInfoService.get(shApplyInfo.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(shApplyInfo.getId())){
				ShApplyInfo shApplyInfoChild = new ShApplyInfo();
				shApplyInfoChild.setParent(new ShApplyInfo(shApplyInfo.getParent().getId()));
				List<ShApplyInfo> list = shApplyInfoService.findList(shApplyInfo); 
				if (list.size() > 0){
					shApplyInfo.setSort(list.get(list.size()-1).getSort());
					if (shApplyInfo.getSort() != null){
						shApplyInfo.setSort(shApplyInfo.getSort() + 30);
					}
				}
			}
		}
		if (shApplyInfo.getSort() == null){
			shApplyInfo.setSort(30);
		}
		model.addAttribute("shApplyInfo", shApplyInfo);
		return "modules/sh/apply/shApplyInfoForm";
	}

	@RequiresPermissions("apply:shApplyInfo:edit")
	@RequestMapping(value = "save")
	public String save(ShApplyInfo shApplyInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shApplyInfo)){
			return form(shApplyInfo, model);
		}
		shApplyInfoService.save(shApplyInfo);
		addMessage(redirectAttributes, "保存报名信息成功");
		return "redirect:"+Global.getAdminPath()+"/apply/shApplyInfo/?repage";
	}
	
	@RequiresPermissions("apply:shApplyInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ShApplyInfo shApplyInfo, RedirectAttributes redirectAttributes) {
		shApplyInfoService.delete(shApplyInfo);
		addMessage(redirectAttributes, "删除报名信息成功");
		return "redirect:"+Global.getAdminPath()+"/apply/shApplyInfo/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ShApplyInfo> list = shApplyInfoService.findList(new ShApplyInfo());
		for (int i=0; i<list.size(); i++){
			ShApplyInfo e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}