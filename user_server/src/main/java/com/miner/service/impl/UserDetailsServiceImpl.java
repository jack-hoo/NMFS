package com.miner.service.impl;

import com.miner.common.exception.MinerException;
import com.miner.dao.SysPermissionDao;
import com.miner.dao.SysRoleDao;
import com.miner.dao.SysUserDao;
import com.miner.dto.UserDetailsModel;
import com.miner.entity.SysPermissionEntity;
import com.miner.entity.SysRoleEntity;
import com.miner.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/26.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Override
    public UserDetailsModel loadUserByUsername(String account) {
        SysUserEntity user = sysUserDao.queryByUsername(account);
        UserDetailsModel userDetails = new UserDetailsModel();
        if (user != null){
            userDetails.setUserId(user.getUserId());
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getPassword());
            userDetails.setEnabled(user.isEnable());
            userDetails.setLastPasswordResetDate(user.getLastPasswordResetDate());
            //添加角色
            Set<GrantedAuthority> authorities = new HashSet<>();
            List<SysRoleEntity> roleEntities = sysRoleDao.queryRoleByUserId(user.getUserId());
            authorities.addAll(roleEntities);
            //授予权限
            for (SysRoleEntity roleEntity : roleEntities){
                List<SysPermissionEntity> sysPermissionEntities = sysPermissionDao.queryByRoleId(roleEntity.getRoleId());
                authorities.addAll(sysPermissionEntities);
            }
            userDetails.setAuthorities(authorities);
        }else {
            throw new MinerException("用户不存在",405);
        }

        return userDetails;
    }
}
