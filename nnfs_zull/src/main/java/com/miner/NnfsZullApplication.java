package com.miner;

import com.miner.authenticate_filter.JWTAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
public class NnfsZullApplication {

	public static void main(String[] args) {
		SpringApplication.run(NnfsZullApplication.class, args);
	}
	@Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
	    return new JWTAuthenticationFilter();
    }
}
