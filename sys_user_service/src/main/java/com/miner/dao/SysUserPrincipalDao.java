package com.miner.dao;

import com.miner.entity.SysUserPrincipalEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@Mapper
public interface SysUserPrincipalDao extends BaseDao<SysUserPrincipalEntity> {
	SysUserPrincipalEntity queryByUserName(String username);
}
