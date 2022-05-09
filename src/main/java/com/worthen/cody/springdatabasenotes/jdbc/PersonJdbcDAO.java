package com.worthen.cody.springdatabasenotes.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.worthen.cody.springdatabasenotes.domain.Person;

@Repository
public class PersonJdbcDAO {

	/**
	 * We can create a custom RowMapper if the data that comes back from a query is
	 * of a different structure/format than our bean (in this case Person and
	 * Person's encapsulated data).
	 */
	private class PersonRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			// map it from the table to a Person object
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
	}

	private JdbcTemplate jdbcTemplate;

	public PersonJdbcDAO(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Uses static SQL to return a List of Person objects from db. Demonstrates how
	 * to use our own custom RowMapper defined in PersonJdbcDAO.
	 */
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person",
				new PersonRowMapper());
	}

	/**
	 * Uses static SQL to return a (unique) Person by id from db.
	 */
	public Person findById(int id) {
		// the bean for which the BeanPropertyRowMapper is defined needs to have a
		// default constructor
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
