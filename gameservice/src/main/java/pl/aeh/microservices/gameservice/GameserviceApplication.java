package pl.aeh.microservices.gameservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO Dodać dodawanie gry na start. Controller i Serwis.

@SpringBootApplication
public class GameserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameserviceApplication.class, args);
    }
}
