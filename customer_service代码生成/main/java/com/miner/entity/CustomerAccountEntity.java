package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户账户表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class CustomerAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long cAccountId;
	//套餐总流量
	private Long dataFlowTotal;
	//客户id
	private Long customerId;

	/**
	 * 设置：
	 */
	public void setCAccountId(Long cAccountId) {
		this.cAccountId = cAccountId;
	}
	/**
	 * 获取：
	 */
	public Long getCAccountId() {
		return cAccountId;
	}
	/**
	 * 设置：套餐总流量
	 */
	public void setDataFlowTotal(Long dataFlowTotal) {
		this.dataFlowTotal = dataFlowTotal;
	}
	/**
	 * 获取：套餐总流量
	 */
	public Long getDataFlowTotal() {
		return dataFlowTotal;
	}
	/**
	 * 设置：客户id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户id
	 */
	public Long getCustomerId() {
		return customerId;
	}
}
