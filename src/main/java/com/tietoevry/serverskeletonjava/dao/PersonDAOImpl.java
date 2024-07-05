package com.tietoevry.serverskeletonjava.dao;

import org.springframework.stereotype.Repository;

public class PersonDAOImpl {
}
/*
@Repository
public class PersonDAOImpl implements PersonDAO {

    private JdbcTemplate jdbcTemplate;

    // constructor injection
    public PersonDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addPerson(PersonDTO person) {
        // implementation...
    }

    @Override
    public List<PersonDTO> getPersons() {
        // implementation...
    }

    // other methods...
}
*
 */