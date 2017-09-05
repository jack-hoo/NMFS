package com.miner.controller;

import com.miner.common.utils.R;
import com.miner.entity.CustomerPrincipalEntity;
import com.miner.entity.HostEntity;
import com.miner.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc: 主播信息控制器
 * Created by hushangjie on 2017/9/5.
 */
@RestController
@RequestMapping("/host")
public class HostController {
    @Autowired
    private HostService hostService;

    @PostMapping("")
    @PreAuthorize(value = "hasRole('MASTER')")
    public R addHost(@RequestBody HostEntity hostEntity){
        CustomerPrincipalEntity hostPrincipal = hostService.save(hostEntity);
        return R.ok().put("host",hostPrincipal);
    }

}
