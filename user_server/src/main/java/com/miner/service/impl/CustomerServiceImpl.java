package com.miner.service.impl;

import com.miner.common.Constant.RoleConstant;
import com.miner.dao.*;
import com.miner.entity.*;
import com.miner.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by hushangjie on 2017/9/10.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RandomSlaveDao randomSlaveDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private HostDao hostDao;
    @Override
    @Transactional
    public void addHost(HostEntity hostEntity,SysUserEntity currentUser) {
        //生成随机账号
        RandomSlaveEntity randomSlaveEntity = generateRandomSlave(currentUser.getUserId());
        //生成账号密码
        SysUserEntity slaveEntity = saveSlaveUser(hostEntity.getHostPhone(),randomSlaveEntity.getSlaveAccount().toString());
        //设置角色对应关系
        setUserRole(RoleConstant.HOST,slaveEntity.getUserId());
        //保存主播信息
        HostEntity master = hostDao.queryByCId(currentUser.getUserId());
        hostEntity.setOrgId(master.getOrgId());
        hostEntity.setCId(slaveEntity.getUserId());
        hostEntity.setCreateTime(new Date());
        hostDao.save(hostEntity);

    }

    /**
     * 审批主播，发送邮件通知
     * @param hostId
     * @param masterId
     */
    @Override
    public void approvalHost(Long hostId, Long masterId) {
        //生成随机账号
        RandomSlaveEntity randomSlaveEntity = generateRandomSlave(masterId);
        HostEntity hostEntity = hostDao.queryObject(hostId);
        //生成账号密码
        SysUserEntity slaveEntity = saveSlaveUser(hostEntity.getHostPhone(),randomSlaveEntity.getSlaveAccount().toString());
        //设置角色对应关系
        setUserRole(RoleConstant.HOST,slaveEntity.getUserId());
        hostEntity.setCId(slaveEntity.getUserId());
        hostDao.update(hostEntity);

    }

    //生成随机账户工具
    public RandomSlaveEntity generateRandomSlave(Long userId){
        RandomSlaveEntity randomSlaveEntity = new RandomSlaveEntity();
        randomSlaveEntity.setMasterId(userId);
        randomSlaveDao.save(randomSlaveEntity);
        return randomSlaveEntity;
    }
    //保存子帐号(将用户注册是的手机号后6位作为登录密码)
    public SysUserEntity saveSlaveUser(String phone, String username){
        SysUserEntity slaveEntity = new SysUserEntity();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = phone.substring(
                phone.length()-6,phone.length());

        slaveEntity.setUsername(username);
        slaveEntity.setPassword(encoder.encode(rawPassword));
        //设置默认属性
        slaveEntity.setCreateTime(new Date());
        slaveEntity.setEnable(true);
        slaveEntity.setLastPasswordResetDate(new Date());
        sysUserDao.save(slaveEntity);
        return slaveEntity;
    }
    //设置角色用户之间的关系
    public void setUserRole(String roleName, Long userId){
        SysRoleEntity roleEntity = sysRoleDao.queryByRoleName(roleName);
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setRoleId(roleEntity.getRoleId());
        sysUserRoleEntity.setUserId(userId);
        sysUserRoleDao.save(sysUserRoleEntity);
    }
    //保存主播信息
    public void saveHost(){

    }
}
