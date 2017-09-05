package com.miner.service.impl;

import com.miner.dao.CustomerPrincipalDao;
import com.miner.dao.HostDao;
import com.miner.entity.CustomerPrincipalEntity;
import com.miner.entity.HostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.miner.dao.OrgInfoDao;
import com.miner.entity.OrgInfoEntity;
import com.miner.service.OrgInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("orgInfoService")
public class OrgInfoServiceImpl implements OrgInfoService {
	@Autowired
	private OrgInfoDao orgInfoDao;
	@Autowired
    private CustomerPrincipalDao customerPrincipalDao;
	@Autowired
    private HostDao hostDao;
	@Override
	public OrgInfoEntity queryObject(Long orgId){
		return orgInfoDao.queryObject(orgId);
	}
	
	@Override
	public List<OrgInfoEntity> queryList(Map<String, Object> map){
		return orgInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orgInfoDao.queryTotal(map);
	}

    /**
     * 保存机构信息后需要更新用户主体表，与机构id关联
     * @param orgInfo
     */
	@Override
    @Transactional
	public void save(OrgInfoEntity orgInfo){
	    orgInfo.setCreateTime(new Date());
		orgInfoDao.save(orgInfo);
        //更新用户主表
        CustomerPrincipalEntity principalEntity = customerPrincipalDao.queryByAccount(orgInfo.getcPhone());
        principalEntity.setOrgId(orgInfo.getOrgId());
        customerPrincipalDao.update(principalEntity);
        //将管理员设置为主播
        HostEntity hostEntity = new HostEntity();
        hostEntity.setOrgId(orgInfo.getOrgId());
        hostEntity.setCId(principalEntity.getCId());
        hostEntity.setCreateTime(new Date());
        hostEntity.setHostName(orgInfo.getcName());
        hostEntity.setHostPhone(orgInfo.getcPhone());
        hostEntity.setRemark("您是机构负责人，具有机构最高权限！");
        hostDao.save(hostEntity);
	}
	
	@Override
	public void update(OrgInfoEntity orgInfo){
		orgInfoDao.update(orgInfo);
	}
	
	@Override
	public void delete(Long orgId){
		orgInfoDao.delete(orgId);
	}
	
	@Override
	public void deleteBatch(Long[] orgIds){
		orgInfoDao.deleteBatch(orgIds);
	}
	
}
