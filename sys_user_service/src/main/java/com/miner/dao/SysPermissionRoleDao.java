package com.miner.dao;

import com.miner.entity.SysPermissionEntity;
import com.miner.entity.SysPermissionRoleEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色权限对应表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@Mapper
public interface SysPermissionRoleDao extends BaseDao<SysPermissionRoleEntity> {
	List<SysPermissionEntity> queryByRoleId(Integer roleId);
    List<SysPermissionEntity> queryByUrlAndRoleId(Integer roleId ,String url);
}
