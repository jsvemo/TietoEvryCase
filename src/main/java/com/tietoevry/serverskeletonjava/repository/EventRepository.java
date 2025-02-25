package com.tietoevry.serverskeletonjava.repository;

import com.tietoevry.serverskeletonjava.repository.model.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}