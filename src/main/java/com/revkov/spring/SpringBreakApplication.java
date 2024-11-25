package com.revkov.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBreakApplication {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SpringBreakApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Hello from spring boot 2.1.3 application and Java 11");
		SpringApplication.run(SpringBreakApplication.class, args);
	}
}
