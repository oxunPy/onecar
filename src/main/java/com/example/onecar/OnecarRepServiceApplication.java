package com.example.onecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OnecarRepServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnecarRepServiceApplication.class, args);
	}

}
