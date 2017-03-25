/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sh.apply.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 报名信息Entity
 * @author huangyunquan
 * @version 2017-03-25
 */
public class ShApplyInfo extends TreeEntity<ShApplyInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 报名信息标题
	private Integer sort;		// 排序
	private String description;		// 报名描述
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String parentIds;		// 所有上级,号隔开
	private ShApplyInfo parent;		// 上级报名
	private String isHandConfirm;		// 是否需要手动审核报名（0-否 1是）
	
	//程序封装的数据
	private String status; //当前状态
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ShApplyInfo() {
		super();
	}

	public ShApplyInfo(String id){
		super(id);
	}

	@Length(min=0, max=200, message="报名信息标题长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=255, message="所有上级,号隔开长度必须介于 0 和 255 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@JsonBackReference
	public ShApplyInfo getParent() {
		return parent;
	}

	public void setParent(ShApplyInfo parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=1, message="是否需要手动审核报名（0-否 1是）长度必须介于 0 和 1 之间")
	public String getIsHandConfirm() {
		return isHandConfirm;
	}

	public void setIsHandConfirm(String isHandConfirm) {
		this.isHandConfirm = isHandConfirm;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}