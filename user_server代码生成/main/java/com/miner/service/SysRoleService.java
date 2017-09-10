package com.miner.service;

import com.miner.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
public interface SysRoleService {
	
	SysRoleEntity queryObject(Integer roleId);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity sysRole);
	
	void update(SysRoleEntity sysRole);
	
	void delete(Integer roleId);
	
	void deleteBatch(Integer[] roleIds);
}
