package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.SysPermissionEntity;
import com.miner.service.SysPermissionService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 系统权限表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@RestController
@RequestMapping("syspermission")
public class SysPermissionController {
	@Autowired
	private SysPermissionService sysPermissionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysPermissionEntity> sysPermissionList = sysPermissionService.queryList(query);
		int total = sysPermissionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysPermissionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{permId}")
	public R info(@PathVariable("permId") Integer permId){
		SysPermissionEntity sysPermission = sysPermissionService.queryObject(permId);
		
		return R.ok().put("sysPermission", sysPermission);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysPermissionEntity sysPermission){
		sysPermissionService.save(sysPermission);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysPermissionEntity sysPermission){
		sysPermissionService.update(sysPermission);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] permIds){
		sysPermissionService.deleteBatch(permIds);
		
		return R.ok();
	}
	
}
