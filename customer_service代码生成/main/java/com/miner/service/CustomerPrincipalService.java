package com.miner.service;

import com.miner.entity.CustomerPrincipalEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface CustomerPrincipalService {
	
	CustomerPrincipalEntity queryObject(Long cId);
	
	List<CustomerPrincipalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerPrincipalEntity customerPrincipal);
	
	void update(CustomerPrincipalEntity customerPrincipal);
	
	void delete(Long cId);
	
	void deleteBatch(Long[] cIds);
}
