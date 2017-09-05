package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.PricingPackEntity;
import com.miner.service.PricingPackService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 价格套餐
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@RestController
@RequestMapping("pricingpack")
public class PricingPackController {
	@Autowired
	private PricingPackService pricingPackService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PricingPackEntity> pricingPackList = pricingPackService.queryList(query);
		int total = pricingPackService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pricingPackList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{pricingPackId}")
	public R info(@PathVariable("pricingPackId") Integer pricingPackId){
		PricingPackEntity pricingPack = pricingPackService.queryObject(pricingPackId);
		
		return R.ok().put("pricingPack", pricingPack);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody PricingPackEntity pricingPack){
		pricingPackService.save(pricingPack);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody PricingPackEntity pricingPack){
		pricingPackService.update(pricingPack);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] pricingPackIds){
		pricingPackService.deleteBatch(pricingPackIds);
		
		return R.ok();
	}
	
}
