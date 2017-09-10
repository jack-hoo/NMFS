package com.miner.dao;

import com.miner.entity.SysRoleEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-08 18:48:14
 */
@Mapper
@Repository
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
    List<SysRoleEntity> queryRoleByUserId(Long userId);
    SysRoleEntity queryByRoleName(String roleName);
}
