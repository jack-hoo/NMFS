package com.miner.service;

import com.miner.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-07 21:41:11
 */
public interface SysUserService {
	
	SysUserEntity queryObject(Long userId);
	
	List<SysUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);

    SysUserEntity save(SysUserEntity sysUser);
	
	void update(SysUserEntity sysUser);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);

	SysUserEntity queryByUsername(String username);

	String login(String username,String password);

	void changePwd(String oldPwd, String newPwd ,SysUserEntity user);

	void resetPwd(SysUserEntity userEntity ,String newPwd);

	void forbidUser(Long userId);
}
