package com.debargha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ServletComponentScan({"com.debargha.config", "com.assignment.shoppingcartt.service"})
@EnableJpaAuditing
public class ShoppingWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingWebApplication.class, args);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
