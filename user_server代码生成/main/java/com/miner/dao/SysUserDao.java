package com.miner.dao;

import com.miner.entity.SysUserEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Mapper
@Repository
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
}
