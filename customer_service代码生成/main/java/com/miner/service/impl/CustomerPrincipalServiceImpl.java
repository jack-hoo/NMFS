package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.CustomerPrincipalDao;
import com.miner.entity.CustomerPrincipalEntity;
import com.miner.service.CustomerPrincipalService;



@Service("customerPrincipalService")
public class CustomerPrincipalServiceImpl implements CustomerPrincipalService {
	@Autowired
	private CustomerPrincipalDao customerPrincipalDao;
	
	@Override
	public CustomerPrincipalEntity queryObject(Long cId){
		return customerPrincipalDao.queryObject(cId);
	}
	
	@Override
	public List<CustomerPrincipalEntity> queryList(Map<String, Object> map){
		return customerPrincipalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerPrincipalDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerPrincipalEntity customerPrincipal){
		customerPrincipalDao.save(customerPrincipal);
	}
	
	@Override
	public void update(CustomerPrincipalEntity customerPrincipal){
		customerPrincipalDao.update(customerPrincipal);
	}
	
	@Override
	public void delete(Long cId){
		customerPrincipalDao.delete(cId);
	}
	
	@Override
	public void deleteBatch(Long[] cIds){
		customerPrincipalDao.deleteBatch(cIds);
	}
	
}
