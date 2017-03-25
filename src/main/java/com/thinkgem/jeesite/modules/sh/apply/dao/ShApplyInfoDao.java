/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.apply.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApplyInfo;

/**
 * 报名信息DAO接口
 * @author huangyunquan
 * @version 2017-03-25
 */
@MyBatisDao
public interface ShApplyInfoDao extends TreeDao<ShApplyInfo> {
	
}