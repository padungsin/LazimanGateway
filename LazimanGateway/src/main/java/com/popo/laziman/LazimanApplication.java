package com.popo.laziman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LazimanApplication {
	
	public static final String APP_NAME = "LazimanGateway";

	public static void main(String[] args) {
		SpringApplication.run(LazimanApplication.class, args);
	}

}
