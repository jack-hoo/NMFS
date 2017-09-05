package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.EventTypeEntity;
import com.miner.service.EventTypeService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 活动类别
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@RestController
@RequestMapping("eventtype")
public class EventTypeController {
	@Autowired
	private EventTypeService eventTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<EventTypeEntity> eventTypeList = eventTypeService.queryList(query);
		int total = eventTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(eventTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{eventTypeId}")
	public R info(@PathVariable("eventTypeId") Integer eventTypeId){
		EventTypeEntity eventType = eventTypeService.queryObject(eventTypeId);
		
		return R.ok().put("eventType", eventType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody EventTypeEntity eventType){
		eventTypeService.save(eventType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody EventTypeEntity eventType){
		eventTypeService.update(eventType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] eventTypeIds){
		eventTypeService.deleteBatch(eventTypeIds);
		
		return R.ok();
	}
	
}
