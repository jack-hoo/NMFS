package com.miner.service.impl;

import com.miner.client.UserClient;
import com.miner.common.exception.MinerException;
import com.miner.dto.RoleModel;
import com.miner.dto.UserModel;
import com.miner.dto.UserDetailsModel;
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
    private UserClient userClient;

    @Override
    public UserDetailsModel loadUserByUsername(String username) {
        UserModel user = userClient.getUser(username);
        UserDetailsModel userDetails = new UserDetailsModel();
        if (user != null){
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getPassword());
            userDetails.setEnabled(user.isEnable());
            userDetails.setLastPasswordResetDate(user.getLastPasswordResetDate());
            //设置角色
            Set<GrantedAuthority> authorities = new HashSet<>();
            List<RoleModel> roles = userClient.getRoles(username);
            authorities.addAll(roles);
            userDetails.setAuthorities(authorities);
        }else {
            throw new MinerException("用户不存在",405);
        }
        return userDetails;
    }
}
