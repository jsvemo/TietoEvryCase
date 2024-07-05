package com.tietoevry.serverskeletonjava.listener;

import com.tietoevry.serverskeletonjava.client.EventClient;
import com.tietoevry.serverskeletonjava.client.TokenClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class StartupApplicationListener {

    private final EventClient eventClient;


    public StartupApplicationListener(EventClient eventClient, TokenClient tokenClient) {
        this.eventClient = eventClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() {
        System.out.println("Starting...");
        eventClient.start();
    }
}