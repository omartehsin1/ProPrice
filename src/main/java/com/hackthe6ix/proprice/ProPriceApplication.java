package com.hackthe6ix.proprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProPriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProPriceApplication.class, args);
	}

}
