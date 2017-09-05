package com.miner.dao;

import com.miner.entity.EventTypeEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动类别
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@Mapper
public interface EventTypeDao extends BaseDao<EventTypeEntity> {
	
}
