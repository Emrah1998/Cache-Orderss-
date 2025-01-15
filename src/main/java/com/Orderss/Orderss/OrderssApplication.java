package com.Orderss.Orderss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class OrderssApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderssApplication.class, args);
	}

}
