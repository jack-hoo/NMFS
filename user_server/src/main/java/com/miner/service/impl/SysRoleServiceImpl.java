package com.miner.service.impl;

import com.miner.dao.SysUserDao;
import com.miner.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysRoleDao;
import com.miner.entity.SysRoleEntity;
import com.miner.service.SysRoleService;



@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
    private SysUserDao sysUserDao;
	
	@Override
	public SysRoleEntity queryObject(Integer roleId){
		return sysRoleDao.queryObject(roleId);
	}
	
	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map){
		return sysRoleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysRoleDao.queryTotal(map);
	}
	
	@Override
	public void save(SysRoleEntity sysRole){
		sysRoleDao.save(sysRole);
	}
	
	@Override
	public void update(SysRoleEntity sysRole){
		sysRoleDao.update(sysRole);
	}
	
	@Override
	public void delete(Integer roleId){
		sysRoleDao.delete(roleId);
	}
	
	@Override
	public void deleteBatch(Integer[] roleIds){
		sysRoleDao.deleteBatch(roleIds);
	}

    @Override
    public List<SysRoleEntity> getUserRoles(String username) {
	    SysUserEntity userEntity = sysUserDao.queryByUsername(username);
	    if (userEntity != null){
	        return sysRoleDao.queryRoleByUserId(userEntity.getUserId());
        }
        return null;
    }

    @Override
    public SysRoleEntity getRoleByRoleName(String roleName) {
        return sysRoleDao.queryByRoleName(roleName);
    }

}
