package com.orionsolution.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan("com.orionsolution.auth.config")
public class LoginGenericApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginGenericApplication.class, args);
	}

}
