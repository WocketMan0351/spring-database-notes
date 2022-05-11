package com.worthen.cody.springdatabasenotes.spring_data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * JPA can map an Object to a row in a table.
 * 
 * Once we define the entities and their relationships, JPA can take care of
 * identifying the entities and creating the correct queries for you based on
 * the operations you might want to perform.
 * 
 * A JPA implementation (such as Hibernate) becomes responsible for writing the
 * queries. Hibernate implements JPA. If we base our ORM on JPA, we can easily
 * switch implementations in the future if we wish.
 */
@Entity // auto maps to a table called person
@Table(name = "person") // optional, in case table name is different from class name
@NamedQuery(name = "find_all_persons", query = "select p from Person p") // for findAll()
public class Person {

	// instance variables are mapped to the columns in the table

	@Id // required, indicates this is a primary key
	@GeneratedValue // JPA will automatically populate the id (usually with a sequence)
	private int id;

	@Column(name = "name") // optional, in case column name doesn't match
	private String name;

	private String location;
	private Date birthDate;

	// JPA requires a default constructor
	public Person() {
	}

	// So we can create a Person and allow JPA to assign its own value for id
	public Person(String name, String location, Date birthDate) {
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public Person(int id, String name, String location, Date birthDate) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", birthDate="
				+ birthDate + "]";
	}

}
