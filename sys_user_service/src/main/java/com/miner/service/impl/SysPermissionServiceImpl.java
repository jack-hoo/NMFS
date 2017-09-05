package com.miner.service.impl;

import com.miner.dao.SysPermissionRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysPermissionDao;
import com.miner.entity.SysPermissionEntity;
import com.miner.service.SysPermissionService;



@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
    private SysPermissionRoleDao sysPermissionRoleDao;
	@Override
	public SysPermissionEntity queryObject(Integer permId){
		return sysPermissionDao.queryObject(permId);
	}
	
	@Override
	public List<SysPermissionEntity> queryList(Map<String, Object> map){
		return sysPermissionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysPermissionDao.queryTotal(map);
	}
	
	@Override
	public void save(SysPermissionEntity sysPermission){
		sysPermissionDao.save(sysPermission);
	}
	
	@Override
	public void update(SysPermissionEntity sysPermission){
		sysPermissionDao.update(sysPermission);
	}
	
	@Override
	public void delete(Integer permId){
		sysPermissionDao.delete(permId);
	}
	
	@Override
	public void deleteBatch(Integer[] permIds){
		sysPermissionDao.deleteBatch(permIds);
	}

    @Override
    public List<SysPermissionEntity> queryByRoleId(Integer roleId) {
        return sysPermissionRoleDao.queryByRoleId(roleId);
    }


}
