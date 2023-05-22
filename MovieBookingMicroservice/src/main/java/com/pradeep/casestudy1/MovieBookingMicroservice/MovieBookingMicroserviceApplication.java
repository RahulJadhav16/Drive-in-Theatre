package com.pradeep.casestudy1.MovieBookingMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieBookingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingMicroserviceApplication.class, args);
	}

}
