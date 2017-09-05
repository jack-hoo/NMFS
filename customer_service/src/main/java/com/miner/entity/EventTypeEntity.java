package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 活动类别
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class EventTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer eventTypeId;
	//活动类别名称
	private String typeName;
	//
	private String typeDesc;
	//
	private Long orgId;

	/**
	 * 设置：
	 */
	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	/**
	 * 获取：
	 */
	public Integer getEventTypeId() {
		return eventTypeId;
	}
	/**
	 * 设置：活动类别名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取：活动类别名称
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置：
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	/**
	 * 获取：
	 */
	public String getTypeDesc() {
		return typeDesc;
	}
	/**
	 * 设置：
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：
	 */
	public Long getOrgId() {
		return orgId;
	}
}
