package com.worthen.cody.springdatabasenotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.worthen.cody.springdatabasenotes.jdbc.PersonJdbcDAO;

@SpringBootApplication
public class SpringDatabaseNotesApplication implements CommandLineRunner {

	private Logger LOGGER = LoggerFactory.getLogger(SpringDatabaseNotesApplication.class);

	@Autowired
	PersonJdbcDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(SpringDatabaseNotesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// fire a query
		LOGGER.info("All users -> {}", dao.findAll());
		LOGGER.info("User id 10001 -> {}", dao.findById(10001));
	}

}
