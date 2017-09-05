package com.miner.service.impl;

import com.miner.common.exception.MinerException;
import com.miner.common.utils.JwtTokenUtil;
import com.miner.dao.SysUserPrincipalDao;
import com.miner.entity.SysMenuEntity;
import com.miner.entity.SysPermissionEntity;
import com.miner.entity.SysUserPrincipalEntity;
import com.miner.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */
@Service("authenticateService")
public class AuthenticateServiceImpl implements AuthenticateService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysUserPrincipalDao sysUserPrincipalDao;
    @Autowired
    AuthenticationManager myAuthenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        String token = "";
        try {
            //身份认证
            final Authentication authentication = myAuthenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final SysUserPrincipalEntity userDetails = userDetailsService.loadUserByUsername(username);
            token = (jwtTokenUtil.generateToken(userDetails));

        } catch (UsernameNotFoundException e) {
            throw new MinerException("用户名或密码错误!",111);
        } catch (LockedException e){
            throw new MinerException("用户已被锁定!",110);
        } catch (InternalAuthenticationServiceException e){
            throw new MinerException("用户权限不能为空!",112);
        }
        return token;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public String refreshToken(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        SysUserPrincipalEntity userPrincipal = sysUserPrincipalDao.queryByUserName(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, userPrincipal.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public boolean validateToken(String token, SysUserPrincipalEntity user) {
        try {
            return jwtTokenUtil.validateToken(token,user);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<SysMenuEntity> getMenus(String token) {
        SysUserPrincipalEntity user = getUser(token);
        if (user != null){
            //根据用户获取菜单列表

            return null;
        }else {
            throw new MinerException("该用户不存在！");
        }
    }

    @Override
    public List<SysPermissionEntity> getPermissions(String token) {
        SysUserPrincipalEntity user = getUser(token);
        if (user != null){
            //根据用户获取角色
            return null;
        }else {
            throw new MinerException("该用户不存在！");
        }
    }

    @Override
    public SysUserPrincipalEntity getUser(String token) {
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        if (userName != null){
            SysUserPrincipalEntity userPrincipal = sysUserPrincipalDao.queryByUserName(userName);
            return userPrincipal;
        }else {
            return null;
        }

    }
}
