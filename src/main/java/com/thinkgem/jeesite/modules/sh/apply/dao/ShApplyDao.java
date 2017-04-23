package com.thinkgem.jeesite.modules.sh.apply.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sh.apply.entity.ShApply;

/**
 * 报名表DAO接口
 * @author huangyunquan
 * @version 2017-04-09
 */
@MyBatisDao
public interface ShApplyDao extends CrudDao<ShApply> {
	/**
	 * 获取报名信息
	 * @param userId
	 * @return
	 */
	List<ShApply> getListByUserId(@Param("userId")String userId);

	/**
	 * 条件查询
	 * @param apply
	 * @return
	 */
	List<ShApply>  findByCondition(ShApply apply);
	
}