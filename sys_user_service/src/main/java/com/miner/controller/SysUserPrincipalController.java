package com.miner.controller;

import java.util.List;
import java.util.Map;


import com.miner.common.annotation.CurrentUser;
import com.miner.entity.SysUserPrincipalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.miner.service.SysUserPrincipalService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 系统用户
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@RestController
@RequestMapping("user")
public class SysUserPrincipalController {
	@Autowired
	private SysUserPrincipalService sysUserPrincipalService;
	
	/**
	 * 列表
	 */
	@PreAuthorize("hasAuthority('user:list')")
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params ){
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
	@PreAuthorize("#userId == authentication.getPrincipal().userId or hasRole('BOOS')")
	@RequestMapping("/info/{userId}")
	public R info(@PathVariable("userId") Long userId ,@CurrentUser SysUserPrincipalEntity user){
		SysUserPrincipalEntity sysUserPrincipal = sysUserPrincipalService.queryObject(userId);
		return R.ok().put("sysUserPrincipal", sysUserPrincipal);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
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
