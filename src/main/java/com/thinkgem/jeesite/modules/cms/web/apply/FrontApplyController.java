/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.apply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApplyInfo;
import com.thinkgem.jeesite.modules.sh.apply.service.ShApplyInfoService;
import com.thinkgem.jeesite.modules.sh.weixin.utils.Constant;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 报名前台
 */
@Controller
@RequestMapping(value = "${frontPath}/apply")
public class FrontApplyController extends BaseController{
	
	@Autowired
	private ShApplyInfoService shApplyInfoService;
	
	@RequestMapping(value = {"frontLoginUI"})
	public String frontLoginUI(User user, Model model) {
		return "modules/sh/common/loginUI";
	}
	
	/**
	 * 用户报名
	 * @param applyInfoId
	 */
	@RequestMapping(value = {"{applyInfoId}/application"})
	public String userApply(@PathVariable("applyInfoId") String applyInfoId,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		
		User user = UserUtils.getUser();
		String userType = user.getUserType();
		if(!Constant.USER_STUDENT.equals(user.getUserType())){//不是学员就去登录去
			return "redirect:"+Global.getFrontPath()+"/apply/frontLoginUI";
		}
		System.out.println("用户类型: "+userType);
		return "redirect:"+Global.getFrontPath()+"/apply/shApplyInfo/?repage";
	}
	
}
