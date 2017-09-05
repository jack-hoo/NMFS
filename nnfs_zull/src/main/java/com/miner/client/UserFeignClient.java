package com.miner.client;

import com.miner.common.model.UserPrincipal;
import com.miner.common.utils.R;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/8/25.
 */
@FeignClient(name = "authenticate-service")
public interface UserFeignClient {

    @RequestMapping(value = "/auth/validate_token",method = RequestMethod.GET)
    public R validateToken(@RequestParam("token") String token);

}

