package com.miner.service;

import com.miner.entity.CustomerAccountEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户账户表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface CustomerAccountService {
	
	CustomerAccountEntity queryObject(Long cAccountId);
	
	List<CustomerAccountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerAccountEntity customerAccount);
	
	void update(CustomerAccountEntity customerAccount);
	
	void delete(Long cAccountId);
	
	void deleteBatch(Long[] cAccountIds);
}
