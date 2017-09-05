package com.miner.service.impl;

import com.miner.common.exception.MinerException;
import com.miner.common.utils.JwtTokenUtil;
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

import com.miner.dao.CustomerPrincipalDao;
import com.miner.entity.CustomerPrincipalEntity;
import com.miner.service.CustomerPrincipalService;



@Service("customerPrincipalService")
public class CustomerPrincipalServiceImpl implements CustomerPrincipalService {
	@Autowired
	private CustomerPrincipalDao customerPrincipalDao;
    @Autowired
    AuthenticationManager myAuthenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
	@Override
	public CustomerPrincipalEntity queryObject(Long cPrincipalId){
		return customerPrincipalDao.queryObject(cPrincipalId);
	}
	
	@Override
	public List<CustomerPrincipalEntity> queryList(Map<String, Object> map){
		return customerPrincipalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerPrincipalDao.queryTotal(map);
	}

    /**
     * 用户注册流程：短信验证码成功->保存用户主体信息
     * ->填写机构信息(手机号与前面接收验证码手机号一致),保存->将该管理员设置为主播，保存主播信息
     * @param customerPrincipal
     */
	@Override
	public void save(CustomerPrincipalEntity customerPrincipal){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = customerPrincipal.getCPassword();
        customerPrincipal.setCPassword(encoder.encode(rawPassword));
	    customerPrincipal.setLastPasswordResetDate(new Date());
	    //customerPrincipal.setParent(true);
	    customerPrincipal.setCreateTime(new Date());
	    customerPrincipal.setState(0);
		customerPrincipalDao.save(customerPrincipal);

	}
	
	@Override
	public void update(CustomerPrincipalEntity customerPrincipal){
		customerPrincipalDao.update(customerPrincipal);
	}
	
	@Override
	public void delete(Long cPrincipalId){
		customerPrincipalDao.delete(cPrincipalId);
	}
	
	@Override
	public void deleteBatch(Long[] cPrincipalIds){
		customerPrincipalDao.deleteBatch(cPrincipalIds);
	}

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        String token = "";
        try {
            //身份认证
            final Authentication authentication = myAuthenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final CustomerPrincipalEntity userDetails = userDetailsService.loadUserByUsername(username);
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

}
