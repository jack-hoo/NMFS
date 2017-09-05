package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.EventEntity;
import com.miner.service.EventService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 直播活动表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@RestController
@RequestMapping("event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<EventEntity> eventList = eventService.queryList(query);
		int total = eventService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(eventList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{eventId}")
	public R info(@PathVariable("eventId") Long eventId){
		EventEntity event = eventService.queryObject(eventId);
		
		return R.ok().put("event", event);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody EventEntity event){
		eventService.save(event);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody EventEntity event){
		eventService.update(event);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] eventIds){
		eventService.deleteBatch(eventIds);
		
		return R.ok();
	}
	
}
