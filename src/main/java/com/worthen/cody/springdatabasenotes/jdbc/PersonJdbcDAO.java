package com.worthen.cody.springdatabasenotes.jdbc;

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
	 * Uses static SQL to return a List of Person objects from our H2 db.
	 */
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person",
				new BeanPropertyRowMapper<Person>(Person.class));
		// the bean for which the BeanPropertyRowMapper is defined needs to have a
		// default constructor
	}

	public Person findById(int id) {
		return jdbcTemplate.queryForObject(
				"select * from person where id=?",
				new BeanPropertyRowMapper<Person>(Person.class),
				new Object[] { id });
	}

}
