package com.miner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.EventTypeDao;
import com.miner.entity.EventTypeEntity;
import com.miner.service.EventTypeService;



@Service("eventTypeService")
public class EventTypeServiceImpl implements EventTypeService {
	@Autowired
	private EventTypeDao eventTypeDao;
	
	@Override
	public EventTypeEntity queryObject(Integer eventTypeId){
		return eventTypeDao.queryObject(eventTypeId);
	}
	
	@Override
	public List<EventTypeEntity> queryList(Map<String, Object> map){
		return eventTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return eventTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(EventTypeEntity eventType){
		eventTypeDao.save(eventType);
	}
	
	@Override
	public void update(EventTypeEntity eventType){
		eventTypeDao.update(eventType);
	}
	
	@Override
	public void delete(Integer eventTypeId){
		eventTypeDao.delete(eventTypeId);
	}
	
	@Override
	public void deleteBatch(Integer[] eventTypeIds){
		eventTypeDao.deleteBatch(eventTypeIds);
	}
	
}
