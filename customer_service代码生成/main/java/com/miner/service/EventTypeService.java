package com.miner.service;

import com.miner.entity.EventTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 活动类别
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface EventTypeService {
	
	EventTypeEntity queryObject(Integer eventTypeId);
	
	List<EventTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EventTypeEntity eventType);
	
	void update(EventTypeEntity eventType);
	
	void delete(Integer eventTypeId);
	
	void deleteBatch(Integer[] eventTypeIds);
}
