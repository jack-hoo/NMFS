package com.miner.client;

import com.miner.dto.RoleModel;
import com.miner.dto.UserModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hushangjie on 2017/9/8.
 */
@FeignClient(name = "user-service",url = "http://localhost:8084")
public interface UserClient {
    @GetMapping("/role")
    List<RoleModel> getRoles(@RequestParam(value = "username",required = true) String username);
    @GetMapping("/user")
    UserModel getUser(@RequestParam(value = "username",required = true) String username);
}
