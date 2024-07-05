package com.tietoevry.serverskeletonjava.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tietoevry.serverskeletonjava.dto.EventDTO;
import com.tietoevry.serverskeletonjava.repository.model.model.Event;
import com.tietoevry.serverskeletonjava.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class EventClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventClient.class);
    private WebClient webClient;
    private TokenClient tokenClient;
    List<String> eventStorage = new CopyOnWriteArrayList<>();
    @Autowired
    EventService eventService = new EventService();

    @Autowired
    public EventClient(TokenClient tokenClient) {
        this.tokenClient = tokenClient;
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public void start(){
        Mono<String> jsonMono = tokenClient.getToken();
        Mono<String> token = fromJson(jsonMono);
        token.subscribe(this::handleEventSubscription, this::handleException);
        LOGGER.info("Received token: " + token);

    }

    private void handleException(Throwable error) {
        LOGGER.error("handleException ", error);
    }

    private void handleEventSubscription(String authToken) {
        getAllEvents(authToken).subscribe(event -> {
            LOGGER.info("Received event: " + event);
            eventStorage.add(event);
            try {
                eventService.addEvent(event);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Flux<String> getAllEvents(String token) {
        return webClient.get()
                .uri("/events")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToFlux(String.class);
    }

    public Mono<String> getEvent(String token, int sequenceNumber) {
        return webClient.get()
                .uri("/events/{sequenceNumber}")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Flux<String> getEventsFrom(String token, int sequenceNumber) {
        return webClient.get()
                .uri("/events/from/{sequenceNumber}") // change to your events endpoint
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToFlux(String.class);
    }

    public Mono<String> fromJson(Mono<String> jsonMono){
        return jsonMono.map(this::extractTokenFromJson);
    }

    private String extractTokenFromJson(String jsonString) {
        JsonElement jsonElement = JsonParser.parseString(jsonString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return jsonObject.get("token").getAsString();
    }


}
