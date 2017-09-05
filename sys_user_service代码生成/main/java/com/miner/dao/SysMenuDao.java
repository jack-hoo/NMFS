package com.miner.dao;

import com.miner.entity.SysMenuEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-29 12:50:33
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
}
