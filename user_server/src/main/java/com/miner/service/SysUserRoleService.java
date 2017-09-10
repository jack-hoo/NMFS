package com.miner.service;

import com.miner.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-08 18:47:38
 */
public interface SysUserRoleService {
	
	SysUserRoleEntity queryObject(Long id);

	void deleteByUserId(Long userId);
	
	List<SysUserRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserRoleEntity sysUserRole);
	
	void update(SysUserRoleEntity sysUserRole);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
