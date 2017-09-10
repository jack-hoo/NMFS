package com.miner.dao;

import com.miner.entity.HostEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 主播信息表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Mapper
@Repository
public interface HostDao extends BaseDao<HostEntity> {
    HostEntity queryByCId(Long customerId);
}
