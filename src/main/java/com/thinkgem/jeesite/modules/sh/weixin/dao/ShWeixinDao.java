/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sh.weixin.entity.ShWeixin;

/**
 * 微信模块DAO接口
 * @author huangyunquan
 * @version 2017-03-05
 */
@MyBatisDao
public interface ShWeixinDao extends CrudDao<ShWeixin> {
	
}