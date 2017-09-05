package com.miner.dao;

import com.miner.entity.SysUserRoleEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    SysUserRoleEntity queryByUserId(Long userId);
}
