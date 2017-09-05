package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.SysMenuEntity;
import com.miner.service.SysMenuService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 菜单管理
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-29 12:50:33
 */
@RestController
@RequestMapping("sysmenu")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysMenuEntity> sysMenuList = sysMenuService.queryList(query);
		int total = sysMenuService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysMenuList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{menuId}")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenuEntity sysMenu = sysMenuService.queryObject(menuId);
		
		return R.ok().put("sysMenu", sysMenu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysMenuEntity sysMenu){
		sysMenuService.save(sysMenu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysMenuEntity sysMenu){
		sysMenuService.update(sysMenu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] menuIds){
		sysMenuService.deleteBatch(menuIds);
		
		return R.ok();
	}
	
}
