package com.worthen.cody.springdatabasenotes.jdbc;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worthen.cody.springdatabasenotes.domain.Person;

@Repository
public class PersonJdbcDAO {

	private JdbcTemplate jdbcTemplate;

	public PersonJdbcDAO(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Uses static SQL to return a List of Person objects from db.
	 */
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person",
				new BeanPropertyRowMapper<Person>(Person.class));
		// the bean for which the BeanPropertyRowMapper is defined needs to have a
		// default constructor
	}

	/**
	 * Uses static SQL to return a (unique) Person by id from db.
	 */
	public Person findById(int id) {
		return jdbcTemplate.queryForObject(
				"select * from person where id=?",
				new BeanPropertyRowMapper<Person>(Person.class),
				new Object[] { id });
	}

	/**
	 * Uses static SQL to delete a Person by id from db.
	 */
	public int deleteById(int id) {
		return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
	}

	/**
	 * Uses static SQL to return a List of all Persons with matching name from db.
	 */
	public List<Person> findByName(String name) {
		return jdbcTemplate.query(
				"select * from person where name=?",
				new BeanPropertyRowMapper<Person>(Person.class),
				new Object[] { name });
	}

	/**
	 * Uses static SQL to return a List of all Persons with matching location from
	 * db.
	 */
	public List<Person> findByLocation(String location) {
		return jdbcTemplate.query(
				"select * from person where location=?",
				new BeanPropertyRowMapper<Person>(Person.class),
				new Object[] { location });
	}

	/**
	 * Inserts a new Person into the db.
	 */
	public int insert(Person person) {
		return jdbcTemplate.update(
				"insert into person(id, name, location, birth_date) "
						+ "values(?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()) });
	}

	/**
	 * Updates an existing person in the db.
	 */
	public int update(Person person) {
		return jdbcTemplate.update(
				"update person " + "set name = ?, location = ?, birth_date = ? " + "where id = ? ",
				new Object[] {
						person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()),
						person.getId()
				});
	}

}
