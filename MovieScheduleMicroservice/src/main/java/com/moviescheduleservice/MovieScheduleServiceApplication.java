package com.moviescheduleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieScheduleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieScheduleServiceApplication.class, args);
	}
}
