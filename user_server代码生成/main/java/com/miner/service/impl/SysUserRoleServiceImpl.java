package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysUserRoleDao;
import com.miner.entity.SysUserRoleEntity;
import com.miner.service.SysUserRoleService;



@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public SysUserRoleEntity queryObject(Long id){
		return sysUserRoleDao.queryObject(id);
	}
	
	@Override
	public List<SysUserRoleEntity> queryList(Map<String, Object> map){
		return sysUserRoleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserRoleDao.queryTotal(map);
	}
	
	@Override
	public void save(SysUserRoleEntity sysUserRole){
		sysUserRoleDao.save(sysUserRole);
	}
	
	@Override
	public void update(SysUserRoleEntity sysUserRole){
		sysUserRoleDao.update(sysUserRole);
	}
	
	@Override
	public void delete(Long id){
		sysUserRoleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysUserRoleDao.deleteBatch(ids);
	}
	
}
