package com.tietoevry.serverskeletonjava.service;

import com.tietoevry.serverskeletonjava.EventType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class EventOperations {
    // Autowired required services

    private static final Map<EventType, BiConsumer<String, String>> eventTypeActions = new HashMap<>();

    static {
        eventTypeActions.put(EventType.PERSON_CREATED, PersonService::createPerson);
        eventTypeActions.put(EventType.NAME_CHANGE, PersonService::changeName);
        eventTypeActions.put(EventType.SOCSECNUM_CHANGE, PersonService::changeSocSecNum);
        eventTypeActions.put(EventType.ADDRESS_CHANGE, PersonService::changeAddress);
        eventTypeActions.put(EventType.EMAIL_CHANGE, PersonService::changeEmail);
        eventTypeActions.put(EventType.PHONE_CHANGE, PersonService::changePhone);
    }

    @Bean
    public Map<EventType, BiConsumer<String, String>> eventTypeActions() {
        return eventTypeActions;
    }
}