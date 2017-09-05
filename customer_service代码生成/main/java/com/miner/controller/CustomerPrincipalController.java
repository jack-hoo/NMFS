package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.CustomerPrincipalEntity;
import com.miner.service.CustomerPrincipalService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 客户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@RestController
@RequestMapping("customerprincipal")
public class CustomerPrincipalController {
	@Autowired
	private CustomerPrincipalService customerPrincipalService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CustomerPrincipalEntity> customerPrincipalList = customerPrincipalService.queryList(query);
		int total = customerPrincipalService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(customerPrincipalList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{cId}")
	public R info(@PathVariable("cId") Long cId){
		CustomerPrincipalEntity customerPrincipal = customerPrincipalService.queryObject(cId);
		
		return R.ok().put("customerPrincipal", customerPrincipal);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody CustomerPrincipalEntity customerPrincipal){
		customerPrincipalService.save(customerPrincipal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody CustomerPrincipalEntity customerPrincipal){
		customerPrincipalService.update(customerPrincipal);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] cIds){
		customerPrincipalService.deleteBatch(cIds);
		
		return R.ok();
	}
	
}
