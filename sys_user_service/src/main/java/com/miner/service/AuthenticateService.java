package com.miner.service;

import com.miner.entity.SysMenuEntity;
import com.miner.entity.SysPermissionEntity;
import com.miner.entity.SysUserPrincipalEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */
public interface AuthenticateService {

    String login(String username ,String password);

    void logout(String token);

    String refreshToken(String oldToken);

    boolean validateToken(String token , SysUserPrincipalEntity user);
    //根据token获取菜单列表
    List<SysMenuEntity> getMenus(String token);
    //根据token获取权限列表
    List<SysPermissionEntity> getPermissions(String token);
    //获取个人信息
    SysUserPrincipalEntity getUser(String token);


}
