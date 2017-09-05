package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.CustomerAccountDao;
import com.miner.entity.CustomerAccountEntity;
import com.miner.service.CustomerAccountService;



@Service("customerAccountService")
public class CustomerAccountServiceImpl implements CustomerAccountService {
	@Autowired
	private CustomerAccountDao customerAccountDao;
	
	@Override
	public CustomerAccountEntity queryObject(Long cAccountId){
		return customerAccountDao.queryObject(cAccountId);
	}
	
	@Override
	public List<CustomerAccountEntity> queryList(Map<String, Object> map){
		return customerAccountDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerAccountDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerAccountEntity customerAccount){
		customerAccountDao.save(customerAccount);
	}
	
	@Override
	public void update(CustomerAccountEntity customerAccount){
		customerAccountDao.update(customerAccount);
	}
	
	@Override
	public void delete(Long cAccountId){
		customerAccountDao.delete(cAccountId);
	}
	
	@Override
	public void deleteBatch(Long[] cAccountIds){
		customerAccountDao.deleteBatch(cAccountIds);
	}
	
}
