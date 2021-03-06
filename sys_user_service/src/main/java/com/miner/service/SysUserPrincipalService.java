package com.miner.service;

import com.miner.entity.SysUserPrincipalEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
public interface SysUserPrincipalService {

    SysUserPrincipalEntity queryByUsername(String username);

	SysUserPrincipalEntity queryObject(Long userId);
	
	List<SysUserPrincipalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserPrincipalEntity sysUserPrincipal);
	
	void update(SysUserPrincipalEntity sysUserPrincipal);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);

}
