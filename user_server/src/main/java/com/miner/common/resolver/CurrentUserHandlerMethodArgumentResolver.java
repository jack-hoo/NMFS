package com.miner.common.resolver;

import com.miner.common.annotation.CurrentUser;
import com.miner.common.exception.MinerException;
import com.miner.common.utils.JwtTokenUtil;
import com.miner.dao.SysUserDao;
import com.miner.dto.UserDetailsModel;
import com.miner.dto.UserOutPutDTO;
import com.miner.entity.SysUserEntity;
import com.miner.service.SysUserService;
import com.miner.service.impl.SysUserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/26.
 */
@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserEntity userEntity = new SysUserEntity();
        if (authentication.getPrincipal() != "anonymousUser"){
            UserDetailsModel userDetailsModel = (UserDetailsModel) authentication.getPrincipal();
            BeanUtils.copyProperties(userDetailsModel,userEntity);
        }else {
            throw new MinerException("无效的token",401);
        }
        return userEntity;

    }
}
