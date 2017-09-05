package com.miner.controller;

import com.miner.common.utils.R;
import com.miner.entity.CustomerPrincipalEntity;
import com.miner.model.LoginUserModel;
import com.miner.service.CustomerPrincipalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2017/8/31.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private CustomerPrincipalService customerPrincipalService;
    @PostMapping("/register")
    public R registerSysUser(@RequestBody() @Validated LoginUserModel userModel){
        CustomerPrincipalEntity principal = new CustomerPrincipalEntity();
        principal.setCPassword(userModel.getPassword());
        principal.setCAccount(userModel.getUsername());
        principal.setParent(true);
        customerPrincipalService.save(principal);
        return R.ok();
    }
    @PostMapping("/login")
    public R sysLogin(@RequestBody() @Validated LoginUserModel userModel){

        String token = customerPrincipalService.login(userModel.getUsername() ,userModel.getPassword());
        return R.ok().put("token",token).put("username",userModel.getUsername());

    }

}
