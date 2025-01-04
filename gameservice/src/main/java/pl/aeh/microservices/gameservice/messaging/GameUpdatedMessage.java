package pl.aeh.microservices.gameservice.messaging;

import pl.aeh.microservices.gameservice.app.game.GameDto;

import java.util.UUID;

public record GameUpdatedMessage(
        UUID id,
        UUID gameId
) {
    GameUpdatedMessage(GameDto game) {
        this(UUID.randomUUID(), game.id());
    }
}
