package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.RandomSlaveDao;
import com.miner.entity.RandomSlaveEntity;
import com.miner.service.RandomSlaveService;



@Service("randomSlaveService")
public class RandomSlaveServiceImpl implements RandomSlaveService {
	@Autowired
	private RandomSlaveDao randomSlaveDao;
	
	@Override
	public RandomSlaveEntity queryObject(Long slaveAccount){
		return randomSlaveDao.queryObject(slaveAccount);
	}
	
	@Override
	public List<RandomSlaveEntity> queryList(Map<String, Object> map){
		return randomSlaveDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return randomSlaveDao.queryTotal(map);
	}
	
	@Override
	public void save(RandomSlaveEntity randomSlave){
		randomSlaveDao.save(randomSlave);
	}
	
	@Override
	public void update(RandomSlaveEntity randomSlave){
		randomSlaveDao.update(randomSlave);
	}
	
	@Override
	public void delete(Long slaveAccount){
		randomSlaveDao.delete(slaveAccount);
	}
	
	@Override
	public void deleteBatch(Long[] slaveAccounts){
		randomSlaveDao.deleteBatch(slaveAccounts);
	}
	
}
