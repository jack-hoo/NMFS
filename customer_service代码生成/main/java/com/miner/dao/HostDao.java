package com.miner.dao;

import com.miner.entity.HostEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 主播信息表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@Mapper
public interface HostDao extends BaseDao<HostEntity> {
	
}
