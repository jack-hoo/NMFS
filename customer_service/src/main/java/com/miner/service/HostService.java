package com.miner.service;

import com.miner.entity.CustomerPrincipalEntity;
import com.miner.entity.HostEntity;

import java.util.List;
import java.util.Map;

/**
 * 主播信息表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface HostService {
	
	HostEntity queryObject(Long hostId);
	
	List<HostEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	CustomerPrincipalEntity save(HostEntity host);
	
	void update(HostEntity host);
	
	void delete(Long hostId);
	
	void deleteBatch(Long[] hostIds);
}
