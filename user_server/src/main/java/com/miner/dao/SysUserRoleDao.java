package com.miner.dao;

import com.miner.entity.SysUserRoleEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户与角色对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-08 18:47:38
 */
@Mapper
@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    void deleteByUserId(Long userId);
}
