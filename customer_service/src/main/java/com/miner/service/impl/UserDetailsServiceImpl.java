package com.miner.service.impl;

import com.miner.dao.CustomerPrincipalDao;
import com.miner.entity.CustomerPrincipalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private RedisTemplate redisTemplate;
    @Autowired
    private CustomerPrincipalDao customerPrincipalDao;
    @Override
    public CustomerPrincipalEntity loadUserByUsername(String account) throws UsernameNotFoundException {
        CustomerPrincipalEntity user = customerPrincipalDao.queryByAccount(account);
        //设置角色
        //添加角色,以ROLE_BOOS为例
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.isParent()){
            authorities.add(new SimpleGrantedAuthority("ROLE_MASTER"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_SLAVE"));
        }
        user.setAuthorities(authorities);
        return user;
    }
}
