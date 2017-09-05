package com.miner.dao;

import com.miner.entity.OrgInfoEntity;
import com.miner.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机构客户信息表(平台后台添加)
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@Mapper
public interface OrgInfoDao extends BaseDao<OrgInfoEntity> {

}
