package com.miner.controller;

import com.miner.common.utils.R;
import com.miner.entity.SysUserPrincipalEntity;
import com.miner.model.LoginUserModel;
import com.miner.service.AuthenticateService;
import com.miner.service.SysUserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/8/24.
 */
@RestController
@RequestMapping("/auth")
public class SysAuthController {
    @Autowired
    private SysUserPrincipalService sysUserPrincipalService;
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AuthenticateService authenticateService;

    /**
     * 系统用户登录
     * @param userModel
     * @return
     */
    @PostMapping("/login")
    public R sysLogin(@RequestBody() @Validated LoginUserModel userModel){

        String token = authenticateService.login(userModel.getUsername() ,userModel.getPassword());
        return R.ok().put("token",token).put("username",userModel.getUsername());

    }

    /**
     * 注册系统用户
     * @param sysUserPrincipal
     * @return
     */
    @PostMapping("/register")
    public R registerSysUser(@RequestBody SysUserPrincipalEntity sysUserPrincipal){
        sysUserPrincipal.setStatus(true);
        sysUserPrincipalService.save(sysUserPrincipal);

        return R.ok();
    }

    /**
     * 更新token
     * @param request
     * @return
     */
    @GetMapping("/refresh_token")
    public R refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authenticateService.refreshToken(token);
        if (refreshedToken == null){
            return R.error(400,"token校验错误！");
        }else {
            return R.ok().put("new_token",refreshedToken);
        }
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    @GetMapping("/validate_token")
    public R validateToken(@RequestParam(value = "token",required = true) String token) {

        SysUserPrincipalEntity user = authenticateService.getUser(token);
        if (authenticateService.validateToken(token, user)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/sysUser")
    public SysUserPrincipalEntity getUserByUserName(@RequestParam(value = "username",required = true) String username){
        return sysUserPrincipalService.queryByUsername(username);

    }


}
