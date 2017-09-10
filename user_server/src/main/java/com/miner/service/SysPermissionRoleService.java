package com.miner.service;

import com.miner.entity.SysPermissionRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统角色权限对应表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 22:23:29
 */
public interface SysPermissionRoleService {
	
	SysPermissionRoleEntity queryObject(Integer id);
	
	List<SysPermissionRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysPermissionRoleEntity sysPermissionRole);
	
	void update(SysPermissionRoleEntity sysPermissionRole);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
