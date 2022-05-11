package com.worthen.cody.springdatabasenotes.springJDBC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.worthen.cody.springdatabasenotes.springJDBC.domain.Person;
import com.worthen.cody.springdatabasenotes.springJDBC.jdbc.PersonJdbcDAO;

@SpringBootApplication
public class SpringJDBCApplication implements CommandLineRunner {

	private Logger LOGGER = LoggerFactory.getLogger(SpringJDBCApplication.class);

	@Autowired
	private PersonJdbcDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJDBCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// fires queries
		LOGGER.info("All users -> {}\n", dao.findAll());

		LOGGER.info("User with id=10001 -> {}\n", dao.findById(10001));

		LOGGER.info("User(s) with name=Tank -> {}\n", dao.findByName("Tank"));

		LOGGER.info("User(s) with location=Gilbert, AZ -> {}\n", dao.findByLocation("Gilbert, AZ"));

		LOGGER.info("Deleting user with id=10004 -> ({}) user deleted\n", dao.deleteById(10004));

		LOGGER.info("Inserting user with id=10005 -> ({}) user inserted\n",
				dao.insert(new Person(10005, "Buttercup", "Gilbert, AZ", new java.sql.Date(0))));

		LOGGER.info("User with id=10005 -> {}\n", dao.findById(10005));

		LOGGER.info("Updating user with id=10005 -> ({}) user updated\n",
				dao.update(new Person(10005, "Uhtred", "Bebbanburg", new java.sql.Date(0))));

		LOGGER.info("User with id=10005 -> {}\n", dao.findById(10005));
	}

}
