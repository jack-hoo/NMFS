package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysUserPrincipalDao;
import com.miner.entity.SysUserPrincipalEntity;
import com.miner.service.SysUserPrincipalService;



@Service("sysUserPrincipalService")
public class SysUserPrincipalServiceImpl implements SysUserPrincipalService {
	@Autowired
	private SysUserPrincipalDao sysUserPrincipalDao;
	
	@Override
	public SysUserPrincipalEntity queryObject(Long userId){
		return sysUserPrincipalDao.queryObject(userId);
	}
	
	@Override
	public List<SysUserPrincipalEntity> queryList(Map<String, Object> map){
		return sysUserPrincipalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserPrincipalDao.queryTotal(map);
	}
	
	@Override
	public void save(SysUserPrincipalEntity sysUserPrincipal){
		sysUserPrincipalDao.save(sysUserPrincipal);
	}
	
	@Override
	public void update(SysUserPrincipalEntity sysUserPrincipal){
		sysUserPrincipalDao.update(sysUserPrincipal);
	}
	
	@Override
	public void delete(Long userId){
		sysUserPrincipalDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		sysUserPrincipalDao.deleteBatch(userIds);
	}
	
}
