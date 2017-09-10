package com.miner.common.resolver;

import com.miner.common.annotation.CurrentUser;
import com.miner.dto.UserDetailsModel;
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

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        UserDetailsModel currentUser = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.print(currentUser.getUsername());
        //CustomerPrincipalEntity principalEntity = customerPrincipalDao.queryByAccount(currentUser.getUsername());
        return currentUser;
    }
}
