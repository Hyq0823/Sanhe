package com.thinkgem.jeesite.modules.sh.apply.service;

import java.util.Date;
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CodeUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sh.apply.dao.ShApplyDao;
import com.thinkgem.jeesite.modules.sh.apply.dao.ShApplyInfoDao;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApply;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApplyInfo;

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
	
	@Autowired
	private ShApplyInfoDao shApplyInfoDao;

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


	/**
	 * 根据报名信息id和用户id查询报名情况
	 * @param infoId
	 * @param userId
	 * @return
	 */
	public ShApply findApplyByInfoIdAndUserId(String infoId, String userId) {
		ShApply apply = new ShApply(infoId, userId);
		List<ShApply> list = shApplyDao.findByCondition(apply);
		if(list == null || list.size() == 0) return null;
		return list.get(0);
	}

	/**
	 * 首次报名
	 * @param infoId
	 * @param userId
	 */
	@Transactional(readOnly = false)
	public void firstSaveApply(String infoId, String userId,String isHandConfirm) {
		ShApply apply = new ShApply(infoId, userId);
		apply.setApplyNo(CodeUtils.getInstance().getApplyCode());//设置报名号
		if(StringUtils.isEmpty(isHandConfirm) || "0".equals(isHandConfirm) ){
			apply.setApplyStatus(ShApply.PASS);
		}else{
			apply.setApplyStatus(ShApply.STANDBY_ENSURE) ;
		}
		apply.preInsert();
		apply.setDelFlag("0");
		Date date = new Date();
		apply.setCreateDate(date);
		apply.setUpdateDate(date);
		shApplyDao.insert(apply);
		shApplyInfoDao.increaseApplyNum(infoId);
	}
	
}