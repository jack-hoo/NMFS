package com.miner.service;

import com.miner.entity.CustomerOrgEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户机构关联表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
public interface CustomerOrgService {
	
	CustomerOrgEntity queryObject(Long customerOrgId);
	
	List<CustomerOrgEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerOrgEntity customerOrg);
	
	void update(CustomerOrgEntity customerOrg);
	
	void delete(Long customerOrgId);
	
	void deleteBatch(Long[] customerOrgIds);
}
