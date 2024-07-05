package com.tietoevry.serverskeletonjava;

import com.tietoevry.serverskeletonjava.client.EventClient;
import com.tietoevry.serverskeletonjava.client.TokenClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableJpaRepositories("com.tietoevry.serverskeletonjava.repository")
@ComponentScan("com.tietoevry.serverskeletonjava")
public class ServerSkeletonJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSkeletonJavaApplication.class, args);
    }
}
