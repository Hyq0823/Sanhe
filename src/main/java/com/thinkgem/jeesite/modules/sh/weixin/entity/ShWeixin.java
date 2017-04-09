/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.weixin.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信模块Entity
 * @author huangyunquan
 * @version 2017-03-05
 */
public class ShWeixin extends DataEntity<ShWeixin> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 微信唯一标示
	private User user;		// 关联的用户id
	
	public ShWeixin() {
		super();
	}

	public ShWeixin(String id){
		super(id);
	}
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}