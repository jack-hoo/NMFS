package com.miner.dao;

import com.miner.entity.OrgInfoEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 机构客户信息表(平台后台添加)
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Mapper
@Repository
public interface OrgInfoDao extends BaseDao<OrgInfoEntity> {
	
}
