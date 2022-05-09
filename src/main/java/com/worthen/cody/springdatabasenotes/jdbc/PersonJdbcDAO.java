package com.worthen.cody.springdatabasenotes.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worthen.cody.springdatabasenotes.domain.Person;

@Repository
public class PersonJdbcDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PersonJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// select * from person
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person",
				new BeanPropertyRowMapper<Person>(Person.class));
	}

}
