package com.miner.service.impl;

import com.miner.common.exception.MinerException;
import com.miner.common.utils.JwtTokenUtil;
import com.miner.entity.SysUserPrincipalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.dao.SysUserPrincipalDao;
import com.miner.service.SysUserPrincipalService;



@Service("sysUserPrincipalService")
public class SysUserPrincipalServiceImpl implements SysUserPrincipalService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private SysUserPrincipalDao sysUserPrincipalDao;

    @Override
    public SysUserPrincipalEntity queryByUsername(String username) {
        return sysUserPrincipalDao.queryByUserName(username);
    }

    @Override
	public SysUserPrincipalEntity queryObject(Long userId){
		return sysUserPrincipalDao.queryObject(userId);
	}
	
	@Override
	public List<SysUserPrincipalEntity> queryList(Map<String, Object> map){
		return sysUserPrincipalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserPrincipalDao.queryTotal(map);
	}

	@Override
	public void save(SysUserPrincipalEntity sysUserPrincipal){
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			final String rawPassword = sysUserPrincipal.getPassword();
			sysUserPrincipal.setPassword(encoder.encode(rawPassword));
			sysUserPrincipalDao.save(sysUserPrincipal);

		}catch (Exception e){
			e.printStackTrace();
			throw new MinerException("该用户已存在！");
		}

	}

	@Override
	public void update(SysUserPrincipalEntity sysUserPrincipal){
		sysUserPrincipalDao.update(sysUserPrincipal);
	}
	
	@Override
	public void delete(Long userId){
		sysUserPrincipalDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		sysUserPrincipalDao.deleteBatch(userIds);
	}

}
