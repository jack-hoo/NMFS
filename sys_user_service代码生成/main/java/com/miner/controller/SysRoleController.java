package com.miner.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.SysRoleEntity;
import com.miner.service.SysRoleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;




/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 16:31:54
 */
@RestController
@RequestMapping("sysrole")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sysrole:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
		int total = sysRoleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysRoleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sysrole:info")
	public R info(@PathVariable("roleId") Integer roleId){
		SysRoleEntity sysRole = sysRoleService.queryObject(roleId);
		
		return R.ok().put("sysRole", sysRole);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sysrole:save")
	public R save(@RequestBody SysRoleEntity sysRole){
		sysRoleService.save(sysRole);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sysrole:update")
	public R update(@RequestBody SysRoleEntity sysRole){
		sysRoleService.update(sysRole);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sysrole:delete")
	public R delete(@RequestBody Integer[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
	
}
