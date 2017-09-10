package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.CustomerOrgDao;
import com.miner.entity.CustomerOrgEntity;
import com.miner.service.CustomerOrgService;



@Service("customerOrgService")
public class CustomerOrgServiceImpl implements CustomerOrgService {
	@Autowired
	private CustomerOrgDao customerOrgDao;
	
	@Override
	public CustomerOrgEntity queryObject(Long customerOrgId){
		return customerOrgDao.queryObject(customerOrgId);
	}
	
	@Override
	public List<CustomerOrgEntity> queryList(Map<String, Object> map){
		return customerOrgDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerOrgDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerOrgEntity customerOrg){
		customerOrgDao.save(customerOrg);
	}
	
	@Override
	public void update(CustomerOrgEntity customerOrg){
		customerOrgDao.update(customerOrg);
	}
	
	@Override
	public void delete(Long customerOrgId){
		customerOrgDao.delete(customerOrgId);
	}
	
	@Override
	public void deleteBatch(Long[] customerOrgIds){
		customerOrgDao.deleteBatch(customerOrgIds);
	}
	
}
