package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.CustomerAccountEntity;
import com.miner.service.CustomerAccountService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 客户账户表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@RestController
@RequestMapping("customeraccount")
public class CustomerAccountController {
	@Autowired
	private CustomerAccountService customerAccountService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CustomerAccountEntity> customerAccountList = customerAccountService.queryList(query);
		int total = customerAccountService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(customerAccountList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{cAccountId}")
	public R info(@PathVariable("cAccountId") Long cAccountId){
		CustomerAccountEntity customerAccount = customerAccountService.queryObject(cAccountId);
		
		return R.ok().put("customerAccount", customerAccount);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody CustomerAccountEntity customerAccount){
		customerAccountService.save(customerAccount);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody CustomerAccountEntity customerAccount){
		customerAccountService.update(customerAccount);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] cAccountIds){
		customerAccountService.deleteBatch(cAccountIds);
		
		return R.ok();
	}
	
}
