package pl.aeh.microservices.rentalservice.app.Game;

import lombok.Getter;

import java.util.UUID;

@Getter
class Game {
    private UUID id;
    private String name;
}
