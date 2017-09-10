package com.miner.dao;

import com.miner.entity.SysUserRoleEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:25
 */
@Mapper
@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
}
