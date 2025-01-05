package pl.aeh.microservices.gameservice.messaging;

import java.util.UUID;

public record GameCreatedMessage(
        UUID id,
        UUID gameId,
        String gameName
) {
}
