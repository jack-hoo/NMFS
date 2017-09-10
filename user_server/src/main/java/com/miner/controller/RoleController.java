package com.miner.controller;

import com.miner.common.utils.R;
import com.miner.entity.SysRoleEntity;
import com.miner.service.SysRoleService;
import com.miner.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hushangjie on 2017/9/8.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("")
    public R getRoleByUserName(@RequestParam(value = "username",required = true) String username){
        List<SysRoleEntity> roleEntities = sysRoleService.getUserRoles(username);
        return R.ok().put("roles",roleEntities);
    }
}
