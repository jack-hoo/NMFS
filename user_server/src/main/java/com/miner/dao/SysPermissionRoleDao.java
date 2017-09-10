package com.miner.dao;

import com.miner.entity.SysPermissionRoleEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统角色权限对应表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 22:23:29
 */
@Mapper
@Repository
public interface SysPermissionRoleDao extends BaseDao<SysPermissionRoleEntity> {
	
}
