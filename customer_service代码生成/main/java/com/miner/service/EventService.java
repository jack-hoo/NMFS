package com.miner.service;

import com.miner.entity.EventEntity;

import java.util.List;
import java.util.Map;

/**
 * 直播活动表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public interface EventService {
	
	EventEntity queryObject(Long eventId);
	
	List<EventEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EventEntity event);
	
	void update(EventEntity event);
	
	void delete(Long eventId);
	
	void deleteBatch(Long[] eventIds);
}
