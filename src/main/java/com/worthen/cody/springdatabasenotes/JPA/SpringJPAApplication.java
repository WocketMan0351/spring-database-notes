package com.worthen.cody.springdatabasenotes.JPA;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.worthen.cody.springdatabasenotes.JPA.entity.Person;
import com.worthen.cody.springdatabasenotes.JPA.jpa.PersonJpaRepository;

@SpringBootApplication
public class SpringJPAApplication implements CommandLineRunner {

	/**
	 * We no longer need to manually create a table in data.sql because JPA
	 * automatically creates the schema for us when we are using an in memory
	 * database.
	 */

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJPAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("User with id 10001 -> {}", repository.findById(10001));

		LOGGER.info("Updating person with id 10001 -> {}",
				repository.update(new Person(10001, "Bella", "Phoenix, AZ", new Date())));

		LOGGER.info("Inserting new user -> {}",
				repository.update(new Person("Bob", "Mesa, AZ", new Date())));
	}

}
