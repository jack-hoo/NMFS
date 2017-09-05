package com.miner.dao;

import com.miner.entity.EventEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播活动表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@Mapper
public interface EventDao extends BaseDao<EventEntity> {
	
}
