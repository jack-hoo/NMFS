package com.miner.authenticate_filter;

import com.miner.client.UserFeignClient;
import com.miner.common.utils.JwtTokenUtil;
import com.miner.common.utils.R;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/25.
 */
public class JWTAuthenticationFilter extends ZuulFilter{
    @Autowired
    private UserFeignClient userFeignClient;
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println("===="+request.getRequestURL());
        String authHeader = request.getHeader(tokenHeader);
        //对无需token就能访问的放行
        if (request.getRequestURL().toString().contains("login")){
            ctx.setSendZuulResponse(true);
            return null;
        }
        //拦截需要token认证的URL
        if (authHeader != null && authHeader.startsWith(tokenHead)){
            final String token = authHeader.substring(tokenHead.length());
            R result =  userFeignClient.validateToken(token);
            String code = result.get("code").toString();

            if (code.equals("500")){
                System.out.print("请求中断");
                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                ctx.setResponseStatusCode(401);// 返回错误码
                ctx.setResponseBody("{\"code\":\"401\",\"res\":\"token is invalid\"}");// 返回错误内容
                return null;
            }
            if (code.equals("200")){
                System.out.print("请求继续");
                ctx.setSendZuulResponse(true);
                return null;
            }
        }else {
            System.out.print("请求中断");
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"code\":\"401\",\"res\":\"token can't be null\"}");// 返回错误内容
            return null;
        }
        return null;
    }
}
