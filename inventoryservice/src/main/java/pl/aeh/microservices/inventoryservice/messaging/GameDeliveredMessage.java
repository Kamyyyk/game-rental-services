package pl.aeh.microservices.inventoryservice.messaging;

import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

record GameDeliveredMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {
    public GameDeliveredMessage(GameDto game, Integer quantity) {
        this(UUID.randomUUID(), game.id(), quantity);
    }
}
