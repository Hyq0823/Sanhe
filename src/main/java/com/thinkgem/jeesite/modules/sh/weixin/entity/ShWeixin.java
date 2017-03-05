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
	private String openId;		// 微信唯一标示
	private User user;		// 关联的用户id
	
	public ShWeixin() {
		super();
	}

	public ShWeixin(String id){
		super(id);
	}

	@Length(min=0, max=64, message="微信唯一标示长度必须介于 0 和 64 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}