package com.miner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NmfsCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NmfsCenterApplication.class, args);
	}
}
