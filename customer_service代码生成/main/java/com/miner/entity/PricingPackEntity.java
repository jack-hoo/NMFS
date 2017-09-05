package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 价格套餐
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class PricingPackEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer pricingPackId;
	//套餐价格表/年
	private Integer price;
	//套餐流量/月
	private Integer packData;

	/**
	 * 设置：
	 */
	public void setPricingPackId(Integer pricingPackId) {
		this.pricingPackId = pricingPackId;
	}
	/**
	 * 获取：
	 */
	public Integer getPricingPackId() {
		return pricingPackId;
	}
	/**
	 * 设置：套餐价格表/年
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：套餐价格表/年
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：套餐流量/月
	 */
	public void setPackData(Integer packData) {
		this.packData = packData;
	}
	/**
	 * 获取：套餐流量/月
	 */
	public Integer getPackData() {
		return packData;
	}
}
