package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.SysRoleMenuEntity;
import com.miner.service.SysRoleMenuService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 角色与菜单对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
@RestController
@RequestMapping("role_menu")
public class SysRoleMenuController {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysRoleMenuEntity> sysRoleMenuList = sysRoleMenuService.queryList(query);
		int total = sysRoleMenuService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysRoleMenuList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		SysRoleMenuEntity sysRoleMenu = sysRoleMenuService.queryObject(id);
		
		return R.ok().put("sysRoleMenu", sysRoleMenu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysRoleMenuEntity[] sysRoleMenus){
	    for (SysRoleMenuEntity menu :sysRoleMenus){
            sysRoleMenuService.save(menu);
        }
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysRoleMenuEntity sysRoleMenu){
		sysRoleMenuService.update(sysRoleMenu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		sysRoleMenuService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
