package com.tietoevry.serverskeletonjava.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tietoevry.serverskeletonjava.client.EventClient;
import com.tietoevry.serverskeletonjava.dto.EventDTO;
import com.tietoevry.serverskeletonjava.repository.PersonRepository;
import com.tietoevry.serverskeletonjava.repository.model.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventClient.class);

    @Autowired
    private static PersonRepository personRepository;

    @Autowired
    private static ObjectMapper mapper;

    protected static void changeSocSecNum(String socSecNum, String newSocSecNum) {
        Optional<Person> personOptional = personRepository.findById(socSecNum);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setSocSecNum(newSocSecNum);
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected static void changeName(String socSecNum, String name) {
        Optional<Person> personOptional = personRepository.findById(socSecNum);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setName(name);
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected static void changeAddress(String socSecNum, String address) {
        Optional<Person> personOptional = personRepository.findById(socSecNum);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setAddress(address);
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected static void changeEmail(String socSecNum, String email) {
        Optional<Person> personOptional = personRepository.findById(socSecNum);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setEmail(email);
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected static void changePhone(String socSecNum, String phone) {
        Optional<Person> personOptional = personRepository.findById(socSecNum);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setPhone(phone);
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void createPerson(Person person) {
        personRepository.save(person);
    }

    public static void createPerson(String socSecNum, String personJson) {
        Optional<Person> personOptional = personRepository.findById(socSecNum);
        if (personOptional.isPresent()) {
            throw new IllegalArgumentException();
        } else {
            try {
                Person person = mapper.readValue(personJson, Person.class);
                personRepository.save(person);
            } catch (JsonProcessingException e){
                LOGGER.error("Error: ", e);
            }
        }
    }

    public static List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public Person getPersonBySsn(String socSecNum) {
        return personRepository.findBySocSecNum(socSecNum);
    }
}