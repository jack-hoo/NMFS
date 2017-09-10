package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.CustomerOrgEntity;
import com.miner.service.CustomerOrgService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 客户机构关联表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@RestController
@RequestMapping("customerorg")
public class CustomerOrgController {
	@Autowired
	private CustomerOrgService customerOrgService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CustomerOrgEntity> customerOrgList = customerOrgService.queryList(query);
		int total = customerOrgService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(customerOrgList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{customerOrgId}")
	public R info(@PathVariable("customerOrgId") Long customerOrgId){
		CustomerOrgEntity customerOrg = customerOrgService.queryObject(customerOrgId);
		
		return R.ok().put("customerOrg", customerOrg);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody CustomerOrgEntity customerOrg){
		customerOrgService.save(customerOrg);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody CustomerOrgEntity customerOrg){
		customerOrgService.update(customerOrg);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] customerOrgIds){
		customerOrgService.deleteBatch(customerOrgIds);
		
		return R.ok();
	}
	
}
