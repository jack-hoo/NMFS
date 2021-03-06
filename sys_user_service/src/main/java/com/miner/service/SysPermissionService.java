package com.miner.service;

import com.miner.entity.SysPermissionEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统权限表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
public interface SysPermissionService {
	
	SysPermissionEntity queryObject(Integer permId);
	
	List<SysPermissionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysPermissionEntity sysPermission);
	
	void update(SysPermissionEntity sysPermission);
	
	void delete(Integer permId);
	
	void deleteBatch(Integer[] permIds);

	List<SysPermissionEntity> queryByRoleId(Integer roleId);
}
