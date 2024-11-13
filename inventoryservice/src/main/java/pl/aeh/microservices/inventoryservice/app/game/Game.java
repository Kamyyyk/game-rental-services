package pl.aeh.microservices.inventoryservice.app.game;

import lombok.Getter;

import java.util.UUID;

@Getter
class Game {
    private UUID id;
    private String name;
}
