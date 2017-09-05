package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.EventDao;
import com.miner.entity.EventEntity;
import com.miner.service.EventService;



@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDao eventDao;
	
	@Override
	public EventEntity queryObject(Long eventId){
		return eventDao.queryObject(eventId);
	}
	
	@Override
	public List<EventEntity> queryList(Map<String, Object> map){
		return eventDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return eventDao.queryTotal(map);
	}
	
	@Override
	public void save(EventEntity event){
		eventDao.save(event);
	}
	
	@Override
	public void update(EventEntity event){
		eventDao.update(event);
	}
	
	@Override
	public void delete(Long eventId){
		eventDao.delete(eventId);
	}
	
	@Override
	public void deleteBatch(Long[] eventIds){
		eventDao.deleteBatch(eventIds);
	}
	
}
