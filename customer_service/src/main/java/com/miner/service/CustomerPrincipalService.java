package com.miner.service;

import com.miner.entity.CustomerPrincipalEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-31 13:12:56
 */
public interface CustomerPrincipalService {
	
	CustomerPrincipalEntity queryObject(Long cPrincipalId);
	
	List<CustomerPrincipalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerPrincipalEntity customerPrincipal);
	
	void update(CustomerPrincipalEntity customerPrincipal);
	
	void delete(Long cPrincipalId);
	
	void deleteBatch(Long[] cPrincipalIds);

	String login(String username, String password);
}
