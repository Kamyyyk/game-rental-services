package pl.aeh.microservices.gameservice.messaging;

import pl.aeh.microservices.gameservice.app.game.GameDto;

import java.util.UUID;

public record GameCreatedMessage (
        UUID id,
        UUID gameId
) {
    GameCreatedMessage(GameDto game) {
        this(UUID.randomUUID(), game.id());
    }
}

