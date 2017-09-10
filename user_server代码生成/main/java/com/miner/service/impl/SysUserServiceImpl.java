package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysUserDao;
import com.miner.entity.SysUserEntity;
import com.miner.service.SysUserService;



@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public SysUserEntity queryObject(Long userId){
		return sysUserDao.queryObject(userId);
	}
	
	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserDao.queryTotal(map);
	}
	
	@Override
	public void save(SysUserEntity sysUser){
		sysUserDao.save(sysUser);
	}
	
	@Override
	public void update(SysUserEntity sysUser){
		sysUserDao.update(sysUser);
	}
	
	@Override
	public void delete(Long userId){
		sysUserDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		sysUserDao.deleteBatch(userIds);
	}
	
}
