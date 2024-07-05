package com.tietoevry.serverskeletonjava.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tietoevry.serverskeletonjava.repository.model.model.Event;
import com.tietoevry.serverskeletonjava.repository.model.model.Person;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {
    private String name;
    private String socSecNum;
    private String address;
    private String email;
    private String phone;

    public static PersonDTO entityToDto(Person person) {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setSocSecNum(person.getSocSecNum());
        personDTO.setName(person.getName());
        personDTO.setAddress(person.getAddress());
        personDTO.setEmail(personDTO.getEmail());
        personDTO.setPhone(personDTO.getPhone());
        return personDTO;
    }

    public static Person dtoToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setSocSecNum(person.getSocSecNum());
        person.setName(personDTO.getName());
        person.setAddress(personDTO.getAddress());
        person.setEmail(personDTO.getEmail());
        person.setPhone(personDTO.getPhone());
        return person;
    }

}
