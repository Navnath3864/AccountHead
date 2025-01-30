package com.app.accounthead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableDiscoveryClient
@SpringBootApplication
public class AccountHeadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountHeadApplication.class, args);
	}
	@Bean
	RestTemplate rt() {
		return new RestTemplate();
	}

}
