package com.miner.dao;

import com.miner.entity.SysUserEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统用户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-07 21:41:11
 */
@Mapper
@Repository
public interface SysUserDao extends BaseDao<SysUserEntity> {
	SysUserEntity queryByUsername(String username);
}
