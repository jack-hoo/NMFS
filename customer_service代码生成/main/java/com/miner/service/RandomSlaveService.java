package com.miner.service;

import com.miner.entity.RandomSlaveEntity;

import java.util.List;
import java.util.Map;

/**
 * 随机生成子帐号辅助表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-10 10:23:03
 */
public interface RandomSlaveService {
	
	RandomSlaveEntity queryObject(Long slaveAccount);
	
	List<RandomSlaveEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RandomSlaveEntity randomSlave);
	
	void update(RandomSlaveEntity randomSlave);
	
	void delete(Long slaveAccount);
	
	void deleteBatch(Long[] slaveAccounts);
}
