package com.miner.service;

import com.miner.entity.HostEntity;
import com.miner.entity.RandomSlaveEntity;
import com.miner.entity.SysUserEntity;

/**
 * Created by hushangjie on 2017/9/10.
 */
public interface CustomerService {
    void addHost(HostEntity hostEntity, SysUserEntity currentUser);
    //SysUserEntity generateSlave(Long masterId);
    void approvalHost(Long hostId, Long masterId);
}
