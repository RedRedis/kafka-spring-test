package com.example.kafkatest.repository;

import com.example.kafkatest.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Person, Long> {
}
