package com.miner.service;

import com.miner.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
public interface SysMenuService {
	
	SysMenuEntity queryObject(Long menuId);
	
	List<SysMenuEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysMenuEntity sysMenu);
	
	void update(SysMenuEntity sysMenu);
	
	void delete(Long menuId);
	
	void deleteBatch(Long[] menuIds);
}
