package com.miner.service;

import com.miner.entity.OrgInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 机构客户信息表(平台后台添加)
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface OrgInfoService {
	
	OrgInfoEntity queryObject(Long orgId);
	
	List<OrgInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrgInfoEntity orgInfo);
	
	void update(OrgInfoEntity orgInfo);
	
	void delete(Long orgId);
	
	void deleteBatch(Long[] orgIds);
}
