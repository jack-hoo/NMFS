package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.PricingPackDao;
import com.miner.entity.PricingPackEntity;
import com.miner.service.PricingPackService;



@Service("pricingPackService")
public class PricingPackServiceImpl implements PricingPackService {
	@Autowired
	private PricingPackDao pricingPackDao;
	
	@Override
	public PricingPackEntity queryObject(Integer pricingPackId){
		return pricingPackDao.queryObject(pricingPackId);
	}
	
	@Override
	public List<PricingPackEntity> queryList(Map<String, Object> map){
		return pricingPackDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return pricingPackDao.queryTotal(map);
	}
	
	@Override
	public void save(PricingPackEntity pricingPack){
		pricingPackDao.save(pricingPack);
	}
	
	@Override
	public void update(PricingPackEntity pricingPack){
		pricingPackDao.update(pricingPack);
	}
	
	@Override
	public void delete(Integer pricingPackId){
		pricingPackDao.delete(pricingPackId);
	}
	
	@Override
	public void deleteBatch(Integer[] pricingPackIds){
		pricingPackDao.deleteBatch(pricingPackIds);
	}
	
}
