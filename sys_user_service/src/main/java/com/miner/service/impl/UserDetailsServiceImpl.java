package com.miner.service.impl;

import com.miner.dao.SysPermissionRoleDao;
import com.miner.dao.SysRoleDao;
import com.miner.dao.SysUserPrincipalDao;
import com.miner.dao.SysUserRoleDao;
import com.miner.entity.SysPermissionEntity;
import com.miner.entity.SysPermissionRoleEntity;
import com.miner.entity.SysRoleEntity;
import com.miner.entity.SysUserPrincipalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/26.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserPrincipalDao sysUserPrincipalDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysPermissionRoleDao sysPermissionRoleDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public SysUserPrincipalEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserPrincipalEntity user = sysUserPrincipalDao.queryByUserName(username);
        //设置权限和角色
        SysRoleEntity roleEntity = sysRoleDao.queryRoleByUserId(user.getUserId());
        Set<GrantedAuthority> authorities = new HashSet<>();
        //添加角色,以ROLE_BOOS为例
        authorities.add(roleEntity);
        //添加权限
        //从redis中获取权限，如果不存在该键则从新从mysql中获取并存入redis
        if (!redisTemplate.hasKey("nmfs:sys:rolePermissions:"+roleEntity.getRoleName())) {
            //缓存不存在,从mysql中获取
            //System.out.print("不存在缓存");
            List<SysPermissionEntity> perms = sysPermissionRoleDao.queryByRoleId(roleEntity.getRoleId());
            if (perms.size() != 0){
                Iterator<SysPermissionEntity> iterator = perms.iterator();
                //批量插入
                redisTemplate.execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        while (iterator.hasNext()){
                            SysPermissionEntity permission = iterator.next();
                            authorities.add(permission);
                            //将结果缓存
                            redisTemplate.opsForSet().add("nmfs:sys:rolePermissions:"+roleEntity.getRoleName(),permission.getPermName());
                        }
                        return null;
                    }
                });


            }
        }else {
            //否则获取角色对应缓存
            Set<Object> perm_in_redis = redisTemplate.opsForSet().members("nmfs:sys:rolePermissions:"+roleEntity.getRoleName());
            if (perm_in_redis.size() != 0){
                Iterator<Object> iterator = perm_in_redis.iterator();
                while (iterator.hasNext()){
                    String permissionName = iterator.next().toString();
                    SysPermissionEntity permissionEntity = new SysPermissionEntity();
                    permissionEntity.setPermName(permissionName);
                    authorities.add(permissionEntity);
                }
            }
        }
        user.setAuthorities(authorities);
        return user;
    }
}
