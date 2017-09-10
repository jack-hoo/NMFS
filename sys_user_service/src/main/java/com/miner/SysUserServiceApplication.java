package com.miner;

import com.miner.common.resolver.CurrentUserHandlerMethodArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
/*@EnableEurekaClient*/
public class SysUserServiceApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(SysUserServiceApplication.class, args);
	}

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserHandlerMethodArgumentResolver());
    }
}
