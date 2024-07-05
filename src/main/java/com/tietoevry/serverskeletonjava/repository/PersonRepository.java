package com.tietoevry.serverskeletonjava.repository;

import com.tietoevry.serverskeletonjava.repository.model.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Person findBySocSecNum(String socSecNum);
}

