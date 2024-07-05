package com.tietoevry.serverskeletonjava.client;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TokenClient {

    private static final String USERNAME = "Donald";
    private static final String PASSWORD = "Fantonald";
    private WebClient webClient;


    public TokenClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public Mono<String> getToken() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", USERNAME);
        map.add("password", PASSWORD);
        return webClient.post()
                .uri("/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(map))
                .exchangeToMono(response -> {
                    if (response.statusCode().isError()) {
                        return response.bodyToMono(String.class)
                                .flatMap(errorMessage -> Mono.error(new RuntimeException("Error: " + errorMessage)));
                    } else {
                        return response.bodyToMono(String.class);
                    }
                });
    }
}