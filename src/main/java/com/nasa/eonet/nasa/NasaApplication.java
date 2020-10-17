package com.nasa.eonet.nasa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.nasa.eonet.nasa")
@EnableJpaRepositories
@EntityScan(basePackages = "com.nasa.eonet")
public class NasaApplication {
	private static final Logger logger = LogManager.getLogger(NasaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NasaApplication.class, args);
		logger.warn("server started");
	}
}
