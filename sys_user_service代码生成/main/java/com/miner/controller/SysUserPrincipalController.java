package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.SysUserPrincipalEntity;
import com.miner.service.SysUserPrincipalService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 系统用户
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-25 09:44:37
 */
@RestController
@RequestMapping("sysuserprincipal")
public class SysUserPrincipalController {
	@Autowired
	private SysUserPrincipalService sysUserPrincipalService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysUserPrincipalEntity> sysUserPrincipalList = sysUserPrincipalService.queryList(query);
		int total = sysUserPrincipalService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysUserPrincipalList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{userId}")
	public R info(@PathVariable("userId") Long userId){
		SysUserPrincipalEntity sysUserPrincipal = sysUserPrincipalService.queryObject(userId);
		
		return R.ok().put("sysUserPrincipal", sysUserPrincipal);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysUserPrincipalEntity sysUserPrincipal){
		sysUserPrincipalService.save(sysUserPrincipal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysUserPrincipalEntity sysUserPrincipal){
		sysUserPrincipalService.update(sysUserPrincipal);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] userIds){
		sysUserPrincipalService.deleteBatch(userIds);
		
		return R.ok();
	}
	
}
