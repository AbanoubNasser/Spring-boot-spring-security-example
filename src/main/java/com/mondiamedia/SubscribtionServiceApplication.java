package com.mondiamedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SubscribtionServiceApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	       return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SubscribtionServiceApplication.class, args);
	}
}
