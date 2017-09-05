package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysMenuDao;
import com.miner.entity.SysMenuEntity;
import com.miner.service.SysMenuService;



@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Override
	public SysMenuEntity queryObject(Long menuId){
		return sysMenuDao.queryObject(menuId);
	}
	
	@Override
	public List<SysMenuEntity> queryList(Map<String, Object> map){
		return sysMenuDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysMenuDao.queryTotal(map);
	}
	
	@Override
	public void save(SysMenuEntity sysMenu){
		sysMenuDao.save(sysMenu);
	}
	
	@Override
	public void update(SysMenuEntity sysMenu){
		sysMenuDao.update(sysMenu);
	}
	
	@Override
	public void delete(Long menuId){
		sysMenuDao.delete(menuId);
	}
	
	@Override
	public void deleteBatch(Long[] menuIds){
		sysMenuDao.deleteBatch(menuIds);
	}
	
}
