package com.m3bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class M3BIApplication {

	public static void main(String[] args) {
		SpringApplication.run(M3BIApplication.class, args);
	}
}
