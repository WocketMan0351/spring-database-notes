package com.worthen.cody.springdatabasenotes.JPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJPAApplication implements CommandLineRunner {

	private Logger LOGGER = LoggerFactory.getLogger(SpringJPAApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJPAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
