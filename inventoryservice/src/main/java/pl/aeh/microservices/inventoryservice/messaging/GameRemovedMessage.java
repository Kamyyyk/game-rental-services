package pl.aeh.microservices.inventoryservice.messaging;

import java.util.UUID;

public record GameRemovedMessage(
        UUID id,
        UUID gameId
) {
    GameRemovedMessage(UUID gameId) {
        this(UUID.randomUUID(), gameId);
    }
}
