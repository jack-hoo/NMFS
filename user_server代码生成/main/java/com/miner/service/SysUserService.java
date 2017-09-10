package com.miner.service;

import com.miner.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
public interface SysUserService {
	
	SysUserEntity queryObject(Long userId);
	
	List<SysUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserEntity sysUser);
	
	void update(SysUserEntity sysUser);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);
}
