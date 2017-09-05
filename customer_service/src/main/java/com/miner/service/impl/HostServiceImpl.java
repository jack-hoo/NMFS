package com.miner.service.impl;

import com.miner.entity.CustomerPrincipalEntity;
import com.miner.service.CustomerPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.miner.dao.HostDao;
import com.miner.entity.HostEntity;
import com.miner.service.HostService;
import org.springframework.transaction.annotation.Transactional;


@Service("hostService")
public class HostServiceImpl implements HostService {
	@Autowired
	private HostDao hostDao;
	@Autowired
    private CustomerPrincipalService customerPrincipalService;
	@Override
	public HostEntity queryObject(Long hostId){
		return hostDao.queryObject(hostId);
	}
	
	@Override
	public List<HostEntity> queryList(Map<String, Object> map){
		return hostDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hostDao.queryTotal(map);
	}

    /**
     * 添加主播，需要为主播分配账号
     * @param host
     */
	@Override
    @Transactional
	public CustomerPrincipalEntity save(HostEntity host){
	    //分配主播账号
        CustomerPrincipalEntity principal = new CustomerPrincipalEntity();
        principal.setOrgId(host.getOrgId());
        principal.setCAccount("155555511111");
        principal.setCreateTime(new Date());
        principal.setState(0);
        principal.setCPassword("511111");
        principal.setParent(false);
        principal.setLastPasswordResetDate(new Date());
        customerPrincipalService.save(principal);

        //保存主播信息
        host.setCId(principal.getCId());
		hostDao.save(host);
		//返回账号主体
        return principal;
	}
	
	@Override
	public void update(HostEntity host){
		hostDao.update(host);
	}
	
	@Override
	public void delete(Long hostId){
		hostDao.delete(hostId);
	}
	
	@Override
	public void deleteBatch(Long[] hostIds){
		hostDao.deleteBatch(hostIds);
	}
	
}
