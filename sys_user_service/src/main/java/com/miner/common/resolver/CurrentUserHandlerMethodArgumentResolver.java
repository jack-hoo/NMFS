package com.miner.common.resolver;

import com.miner.common.annotation.CurrentUser;
import com.miner.dao.SysUserPrincipalDao;
import com.miner.entity.SysUserPrincipalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Administrator on 2017/8/26.
 */
@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Autowired
    private SysUserPrincipalDao sysUserPrincipalDao;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        SysUserPrincipalEntity currentUser = (SysUserPrincipalEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }
}
