package pl.aeh.microservices.reviewservice.app.game;

import lombok.Getter;

import java.util.UUID;

@Getter
class Game {
    private UUID id;
    private String name;
}
