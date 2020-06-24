package com.trade.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the entry point for spring boot application..
 *
 */
@SpringBootApplication
@EnableScheduling
public class TradeStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeStoreAppApplication.class, args);
	}

}
