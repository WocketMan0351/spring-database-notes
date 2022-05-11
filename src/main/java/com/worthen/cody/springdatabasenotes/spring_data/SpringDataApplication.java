package com.worthen.cody.springdatabasenotes.spring_data;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.worthen.cody.springdatabasenotes.spring_data.entity.Person;
import com.worthen.cody.springdatabasenotes.spring_data.jpa.PersonSpringDataRepository;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	/**
	 * We no longer need to manually create a table in data.sql because JPA
	 * automatically creates the schema for us when we are using an in memory
	 * database.
	 */

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * This interface extends the JpaRepository interface which contains common JPA
	 * actions.
	 */
	@Autowired
	private PersonSpringDataRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("User id 10001 -> {}", repository.findById(10001));

		// JpaRepository only has save() not update() or insert()
		LOGGER.info("Inserting user -> {}",
				repository.save(new Person("Mike", "San Diego, CA", new Date())));
	}

}
