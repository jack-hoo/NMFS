package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miner.entity.SysPermissionRoleEntity;
import com.miner.service.SysPermissionRoleService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 系统角色权限对应表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@RestController
@RequestMapping("permission_role")
public class SysPermissionRoleController {
	@Autowired

	private SysPermissionRoleService sysPermissionRoleService;



	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysPermissionRoleEntity> sysPermissionRoleList = sysPermissionRoleService.queryList(query);
		int total = sysPermissionRoleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysPermissionRoleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		SysPermissionRoleEntity sysPermissionRole = sysPermissionRoleService.queryObject(id);
		
		return R.ok().put("sysPermissionRole", sysPermissionRole);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysPermissionRoleEntity sysPermissionRole){
		sysPermissionRoleService.save(sysPermissionRole);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysPermissionRoleEntity sysPermissionRole){
		sysPermissionRoleService.update(sysPermissionRole);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		sysPermissionRoleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
