package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.OrgInfoDao;
import com.miner.entity.OrgInfoEntity;
import com.miner.service.OrgInfoService;



@Service("orgInfoService")
public class OrgInfoServiceImpl implements OrgInfoService {
	@Autowired
	private OrgInfoDao orgInfoDao;
	
	@Override
	public OrgInfoEntity queryObject(Long orgId){
		return orgInfoDao.queryObject(orgId);
	}
	
	@Override
	public List<OrgInfoEntity> queryList(Map<String, Object> map){
		return orgInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orgInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(OrgInfoEntity orgInfo){
		orgInfoDao.save(orgInfo);
	}
	
	@Override
	public void update(OrgInfoEntity orgInfo){
		orgInfoDao.update(orgInfo);
	}
	
	@Override
	public void delete(Long orgId){
		orgInfoDao.delete(orgId);
	}
	
	@Override
	public void deleteBatch(Long[] orgIds){
		orgInfoDao.deleteBatch(orgIds);
	}
	
}
