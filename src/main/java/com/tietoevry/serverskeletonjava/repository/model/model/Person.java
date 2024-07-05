package com.tietoevry.serverskeletonjava.repository.model.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSONS")
@Builder
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class Person {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "PERSON_ID")
    //private Long id;
    @Id
    @Column(name = "SOCIAL_SECURITY_NUMBER")
    private String socSecNum;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    public <T> Person(T t) {
    }
}