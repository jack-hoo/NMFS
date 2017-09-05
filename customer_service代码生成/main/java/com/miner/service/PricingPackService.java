package com.miner.service;

import com.miner.entity.PricingPackEntity;

import java.util.List;
import java.util.Map;

/**
 * 价格套餐
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface PricingPackService {
	
	PricingPackEntity queryObject(Integer pricingPackId);
	
	List<PricingPackEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PricingPackEntity pricingPack);
	
	void update(PricingPackEntity pricingPack);
	
	void delete(Integer pricingPackId);
	
	void deleteBatch(Integer[] pricingPackIds);
}
