package pl.aeh.microservices.inventoryservice.messaging;

import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

record GameLostMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {
    GameLostMessage(GameDto game, Integer quantity) {
        this(UUID.randomUUID(), game.id(), quantity);
    }
}
