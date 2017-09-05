package com.miner.service;

import com.miner.entity.SysMenuEntity;
import com.miner.entity.SysRoleEntity;
import com.miner.entity.SysRoleMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
public interface SysRoleMenuService {

    List<SysMenuEntity> queryMenusByRoleId(Integer roleId);
	
	SysRoleMenuEntity queryObject(Long id);
	
	List<SysRoleMenuEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleMenuEntity sysRoleMenu);
	
	void update(SysRoleMenuEntity sysRoleMenu);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
