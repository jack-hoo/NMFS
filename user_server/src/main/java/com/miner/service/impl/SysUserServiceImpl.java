package com.miner.service.impl;

import com.miner.common.exception.MinerException;
import com.miner.common.utils.DateUtils;
import com.miner.common.utils.JwtTokenUtil;
import com.miner.dto.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.miner.dao.SysUserDao;
import com.miner.entity.SysUserEntity;
import com.miner.service.SysUserService;



@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    AuthenticationManager myAuthenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
	@Override
	public SysUserEntity queryObject(Long userId){
		return sysUserDao.queryObject(userId);
	}
	
	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserDao.queryTotal(map);
	}

    /**
     * 保存用户
     * @param sysUser
     * @return
     */
	@Override
	public SysUserEntity save(SysUserEntity sysUser){
	    //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = sysUser.getPassword();
        sysUser.setPassword(encoder.encode(rawPassword));
        //设置默认属性
        sysUser.setCreateTime(new Date());
        sysUser.setEnable(true);
        sysUser.setLastPasswordResetDate(new Date());
		sysUserDao.save(sysUser);
		return sysUser;
	}
	
	@Override
	public void update(SysUserEntity sysUser){
		sysUserDao.update(sysUser);
	}
	
	@Override
	public void delete(Long userId){
		sysUserDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		sysUserDao.deleteBatch(userIds);
	}

    @Override
    public SysUserEntity queryByUsername(String username) {
        return sysUserDao.queryByUsername(username);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        String token = "";
        try {
            //身份认证
            final Authentication authentication = myAuthenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetailsModel userDetails = userDetailsService.loadUserByUsername(username);
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

    /**
     * 用户通过旧密码来修改密码
     * @param oldPwd
     * @param newPwd
     * @param user
     */
    @Override
    public void changePwd(String oldPwd, String newPwd, SysUserEntity user) {
        //
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPwd,user.getPassword())){
            //密码匹配，重置密码
            user.setPassword(encoder.encode(newPwd));
            user.setLastPasswordResetDate(new Date());
            sysUserDao.update(user);
        }else {
            throw new MinerException("密码错误",410);
        }
    }

    @Override
    public void resetPwd(SysUserEntity userEntity, String newPwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword(encoder.encode(newPwd));
        userEntity.setLastPasswordResetDate(new Date());
        sysUserDao.update(userEntity);
    }

    @Override
    public void forbidUser(Long userId) {
        SysUserEntity userEntity = sysUserDao.queryObject(userId);
        userEntity.setEnable(false);
        sysUserDao.update(userEntity);
    }

}
