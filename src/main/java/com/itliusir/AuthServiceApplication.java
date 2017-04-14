package com.itliusir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@SpringBootApplication
public class AuthServiceApplication{
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
