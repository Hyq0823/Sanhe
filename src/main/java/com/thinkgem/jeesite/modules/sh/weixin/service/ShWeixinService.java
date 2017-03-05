/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sh.weixin.entity.ShWeixin;
import com.thinkgem.jeesite.modules.sh.weixin.dao.ShWeixinDao;

/**
 * 微信模块Service
 * @author huangyunquan
 * @version 2017-03-05
 */
@Service
@Transactional(readOnly = true)
public class ShWeixinService extends CrudService<ShWeixinDao, ShWeixin> {

	public ShWeixin get(String id) {
		return super.get(id);
	}
	
	public List<ShWeixin> findList(ShWeixin shWeixin) {
		return super.findList(shWeixin);
	}
	
	public Page<ShWeixin> findPage(Page<ShWeixin> page, ShWeixin shWeixin) {
		return super.findPage(page, shWeixin);
	}
	
	@Transactional(readOnly = false)
	public void save(ShWeixin shWeixin) {
		super.save(shWeixin);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShWeixin shWeixin) {
		super.delete(shWeixin);
	}
	
}