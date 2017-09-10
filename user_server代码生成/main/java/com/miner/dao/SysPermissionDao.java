package com.miner.dao;

import com.miner.entity.SysPermissionEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统权限表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 22:23:29
 */
@Mapper
@Repository
public interface SysPermissionDao extends BaseDao<SysPermissionEntity> {
	
}
