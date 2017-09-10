package com.miner.controller;

import com.miner.entity.SysUserEntity;
import com.miner.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hushangjie on 2017/9/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService userService;
    @GetMapping("")
    public SysUserEntity getUserByName(@RequestParam(value = "username",required = true) String username){
        return userService.queryByUsername(username);
    }
}
