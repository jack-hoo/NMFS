package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysPermissionRoleDao;
import com.miner.entity.SysPermissionRoleEntity;
import com.miner.service.SysPermissionRoleService;



@Service("sysPermissionRoleService")
public class SysPermissionRoleServiceImpl implements SysPermissionRoleService {
	@Autowired
	private SysPermissionRoleDao sysPermissionRoleDao;
	
	@Override
	public SysPermissionRoleEntity queryObject(Integer id){
		return sysPermissionRoleDao.queryObject(id);
	}
	
	@Override
	public List<SysPermissionRoleEntity> queryList(Map<String, Object> map){
		return sysPermissionRoleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysPermissionRoleDao.queryTotal(map);
	}
	
	@Override
	public void save(SysPermissionRoleEntity sysPermissionRole){
		sysPermissionRoleDao.save(sysPermissionRole);
	}
	
	@Override
	public void update(SysPermissionRoleEntity sysPermissionRole){
		sysPermissionRoleDao.update(sysPermissionRole);
	}
	
	@Override
	public void delete(Integer id){
		sysPermissionRoleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysPermissionRoleDao.deleteBatch(ids);
	}
	
}
