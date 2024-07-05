package com.tietoevry.serverskeletonjava.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.reflect.TypeToken;
import com.tietoevry.serverskeletonjava.EventType;
import com.tietoevry.serverskeletonjava.dto.EventDTO;
import com.tietoevry.serverskeletonjava.repository.EventRepository;
import com.tietoevry.serverskeletonjava.repository.PersonRepository;
import com.tietoevry.serverskeletonjava.repository.model.model.Event;
import com.tietoevry.serverskeletonjava.repository.model.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;

@Service
public class EventService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private Map<EventType, BiConsumer<String, String>> eventTypeActions;

    public EventService() {
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

        eventTypeActions = new HashMap<>();
        eventTypeActions.put(EventType.NAME_CHANGE, (socSecNum, newName) -> {
            Person person = personRepository.findBySocSecNum(socSecNum);
            if (person == null) {
                throw new NoSuchElementException("No Person found with the given Social Security Number");
            }
            person.setName(newName);
            personRepository.save(person);
        });
    }


    public void addEvents(String json) throws JsonProcessingException {
        List<Event> events = mapper.readValue(json, (JavaType) new TypeToken<List<Event>>(){}.getType());
        eventRepository.saveAll(events);
    }

    public String getAllEvents() throws JsonProcessingException {
        Iterable<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        events.forEach(event -> eventDTOs.add(EventDTO.entityToDto(event)));
        return mapper.writeValueAsString(eventDTOs);
    }


    public void addEvent(String json) throws JsonProcessingException {
        EventDTO eventDTO = mapper.readValue(json, EventDTO.class);
        Event event = EventDTO.dtoToEntity(eventDTO);
        eventRepository.save(event);
        EventType eventType = EventType.valueOf(eventDTO.getEventType());
        eventTypeActions.get(eventType).accept(eventDTO.getSocSecNum(), eventDTO.getEventValue());
    }



    @Autowired
    private PersonRepository personRepository;

}


