package com.miner.service.impl;

import com.miner.entity.SysPermissionEntity;
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
		//删除redis中角色权限的缓存

	}
	
	@Override
	public void update(SysPermissionRoleEntity sysPermissionRole){
        //删除redis中角色权限的缓存
		sysPermissionRoleDao.update(sysPermissionRole);
	}
	
	@Override
	public void delete(Integer id){
        sysPermissionRoleDao.delete(id);
        //删除redis中角色权限的缓存
    }
	
	@Override
	public void deleteBatch(Integer[] ids){
        //删除redis中角色权限的缓存
		sysPermissionRoleDao.deleteBatch(ids);
	}

    @Override
    public List<SysPermissionEntity> queryPermsByRoleIdAndUrl(Integer roleId, String url) {
        return sysPermissionRoleDao.queryByUrlAndRoleId(roleId,url);
    }

}
