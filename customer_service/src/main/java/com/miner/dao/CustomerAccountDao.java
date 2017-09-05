package com.miner.dao;

import com.miner.entity.CustomerAccountEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户账户表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@Mapper
public interface CustomerAccountDao extends BaseDao<CustomerAccountEntity> {
	
}
