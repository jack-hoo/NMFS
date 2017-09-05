package com.miner.dao;

import com.miner.entity.CustomerPrincipalEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-31 13:12:56
 */
@Mapper
public interface CustomerPrincipalDao extends BaseDao<CustomerPrincipalEntity> {
	CustomerPrincipalEntity queryByAccount(String account);
}
