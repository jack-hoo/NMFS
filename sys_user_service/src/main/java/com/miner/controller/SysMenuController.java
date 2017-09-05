package com.miner.controller;

import java.util.List;
import java.util.Map;


import com.miner.common.annotation.CurrentUser;
import com.miner.dao.SysUserRoleDao;
import com.miner.entity.SysUserPrincipalEntity;
import com.miner.entity.SysUserRoleEntity;
import com.miner.model.MenuNode;
import com.miner.service.SysRoleMenuService;
import com.miner.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @date 2017-08-24 17:56:49
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取该角色下的菜单列表
     * @param user
     * @return
     */
	@GetMapping("")
    public R getMenuListByUser(@CurrentUser SysUserPrincipalEntity user){
	    SysUserRoleEntity sysUserRoleEntity = sysUserRoleService.queryByUserId(user.getUserId());
	    List<SysMenuEntity> menus = sysRoleMenuService.queryMenusByRoleId(sysUserRoleEntity.getRoleId());
	    return R.ok().put("menus",menus);
    }

	/**
	 * 列表
	 *//*
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysMenuEntity> sysMenuList = sysMenuService.queryList(query);
		int total = sysMenuService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysMenuList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	*/
	
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
