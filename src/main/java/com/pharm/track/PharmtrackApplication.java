package com.pharm.track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PharmtrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmtrackApplication.class, args);
	}

}
