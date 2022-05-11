package com.worthen.cody.springdatabasenotes.JPA.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.worthen.cody.springdatabasenotes.JPA.entity.Person;

@Repository
@Transactional // controls transaction boundaries on CDI managed beans
public class PersonJpaRepository {

	@PersistenceContext
	EntityManager entityManager; // interface for the @PersistenceContext

	/**
	 * Uses the EntityManager interface to find the entity of type Person with a
	 * primary key of id.
	 */
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}

	/**
	 * Uses the EntityManager interface to find the entity of type Person with
	 * primary key of id. If present, updates the entity. Otherwise, the entity
	 * passed in is inserted. There is no difference between update or insert as far
	 * as EntityMangager is concerned.
	 */
	public Person update(Person person) {
		// automatically chooses to either update or insert according to entity id
		return entityManager.merge(person);
	}

}
