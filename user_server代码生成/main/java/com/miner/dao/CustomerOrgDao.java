package com.miner.dao;

import com.miner.entity.CustomerOrgEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户机构关联表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Mapper
@Repository
public interface CustomerOrgDao extends BaseDao<CustomerOrgEntity> {
	
}
