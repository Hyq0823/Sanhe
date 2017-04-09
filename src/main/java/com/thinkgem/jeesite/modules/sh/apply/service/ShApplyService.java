package com.thinkgem.jeesite.modules.sh.apply.service;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sh.apply.dao.ShApplyDao;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApply;

/**
 * 报名表Service
 * @author huangyunquan
 * @version 2017-04-09
 */
@Service
@Transactional(readOnly = true)
public class ShApplyService extends CrudService<ShApplyDao, ShApply> {

	@Autowired
	private ShApplyDao shApplyDao;

	public ShApply get(String id) {
		return super.get(id);
	}
	
	public List<ShApply> findList(ShApply shApply) {
		return super.findList(shApply);
	}
	
	public Page<ShApply> findPage(Page<ShApply> page, ShApply shApply) {
		return super.findPage(page, shApply);
	}
	
	@Transactional(readOnly = false)
	public void save(ShApply shApply) {
		super.save(shApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShApply shApply) {
		super.delete(shApply);
	}
	
	public List<ShApply> getListByUserId(String userId) {
		return shApplyDao.getListByUserId(userId);
	}
	
}