package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.HostDao;
import com.miner.entity.HostEntity;
import com.miner.service.HostService;



@Service("hostService")
public class HostServiceImpl implements HostService {
	@Autowired
	private HostDao hostDao;
	
	@Override
	public HostEntity queryObject(Long hostId){
		return hostDao.queryObject(hostId);
	}
	
	@Override
	public List<HostEntity> queryList(Map<String, Object> map){
		return hostDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hostDao.queryTotal(map);
	}
	
	@Override
	public void save(HostEntity host){
		hostDao.save(host);
	}
	
	@Override
	public void update(HostEntity host){
		hostDao.update(host);
	}
	
	@Override
	public void delete(Long hostId){
		hostDao.delete(hostId);
	}
	
	@Override
	public void deleteBatch(Long[] hostIds){
		hostDao.deleteBatch(hostIds);
	}

    @Override
    public HostEntity queryByCustomerId(Long cId) {
        return hostDao.queryByCId(cId);
    }

}
