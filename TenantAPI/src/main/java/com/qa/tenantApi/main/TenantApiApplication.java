package com.qa.tenantApi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableDiscoveryClient
public class TenantApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantApiApplication.class, args);
	}

}
