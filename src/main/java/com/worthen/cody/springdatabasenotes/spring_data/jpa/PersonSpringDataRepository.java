package com.worthen.cody.springdatabasenotes.spring_data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worthen.cody.springdatabasenotes.spring_data.entity.Person;

public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {

}
