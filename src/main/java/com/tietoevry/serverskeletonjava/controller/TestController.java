package com.tietoevry.serverskeletonjava.controller;

import com.tietoevry.serverskeletonjava.repository.model.model.Person;
import com.tietoevry.serverskeletonjava.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private String dummyToken = "DummyToken";
    String tokenValue;
    List<String> eventsJson;

    private final PersonService personService;

    @GetMapping(value = "/{socSecNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable String socSecNum, @RequestHeader("Authorization") String token) {
        Person person = personService.getPersonBySsn(socSecNum);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

