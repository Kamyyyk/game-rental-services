package pl.aeh.microservices.gameservice.messaging;

import pl.aeh.microservices.gameservice.app.game.GameDto;

import java.util.UUID;

public record GameRemovedMessage(
        UUID id,
        UUID gameId
) {
    GameRemovedMessage(UUID gameId) {
        this(UUID.randomUUID(), gameId);
    }
}
