package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.SysUserRoleEntity;
import com.miner.service.SysUserRoleService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 用户与角色对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@RestController
@RequestMapping("sysuserrole")
public class SysUserRoleController {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysUserRoleEntity> sysUserRoleList = sysUserRoleService.queryList(query);
		int total = sysUserRoleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysUserRoleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		SysUserRoleEntity sysUserRole = sysUserRoleService.queryObject(id);
		
		return R.ok().put("sysUserRole", sysUserRole);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysUserRoleEntity sysUserRole){
		sysUserRoleService.save(sysUserRole);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysUserRoleEntity sysUserRole){
		sysUserRoleService.update(sysUserRole);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		sysUserRoleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
