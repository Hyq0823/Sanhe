/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.apply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sh.apply.dao.ShApplyInfoDao;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApplyInfo;

/**
 * 报名信息Service
 * @author huangyunquan
 * @version 2017-03-25
 */
@Service
@Transactional(readOnly = true)
public class ShApplyInfoService extends TreeService<ShApplyInfoDao, ShApplyInfo> {
	
	@Autowired
	private ShApplyInfoDao shApplyInfoDao;

	public ShApplyInfo get(String id) {
		return super.get(id);
	}
	
	public List<ShApplyInfo> findList(ShApplyInfo shApplyInfo) {
		if (StringUtils.isNotBlank(shApplyInfo.getParentIds())){
			shApplyInfo.setParentIds(","+shApplyInfo.getParentIds()+",");
		}
		return super.findList(shApplyInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ShApplyInfo shApplyInfo) {
		super.save(shApplyInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShApplyInfo shApplyInfo) {
		super.delete(shApplyInfo);
	}

	
	
}