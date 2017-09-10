package com.miner.dao;

import com.miner.entity.RandomSlaveEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 随机生成子帐号辅助表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-10 10:23:03
 */
@Mapper
@Repository
public interface RandomSlaveDao extends BaseDao<RandomSlaveEntity> {
	
}
