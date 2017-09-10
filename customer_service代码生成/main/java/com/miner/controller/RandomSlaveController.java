package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.RandomSlaveEntity;
import com.miner.service.RandomSlaveService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 随机生成子帐号辅助表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-10 10:23:03
 */
@RestController
@RequestMapping("randomslave")
public class RandomSlaveController {
	@Autowired
	private RandomSlaveService randomSlaveService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RandomSlaveEntity> randomSlaveList = randomSlaveService.queryList(query);
		int total = randomSlaveService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(randomSlaveList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{slaveAccount}")
	public R info(@PathVariable("slaveAccount") Long slaveAccount){
		RandomSlaveEntity randomSlave = randomSlaveService.queryObject(slaveAccount);
		
		return R.ok().put("randomSlave", randomSlave);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody RandomSlaveEntity randomSlave){
		randomSlaveService.save(randomSlave);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody RandomSlaveEntity randomSlave){
		randomSlaveService.update(randomSlave);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] slaveAccounts){
		randomSlaveService.deleteBatch(slaveAccounts);
		
		return R.ok();
	}
	
}
