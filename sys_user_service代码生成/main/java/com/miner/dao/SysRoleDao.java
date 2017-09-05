package com.miner.dao;

import com.miner.entity.SysRoleEntity;
import io.renren.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 16:31:54
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
}
