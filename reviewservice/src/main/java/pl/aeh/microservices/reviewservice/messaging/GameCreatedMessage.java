package pl.aeh.microservices.reviewservice.messaging;

import java.util.UUID;

public record GameCreatedMessage(
        UUID id,
        UUID gameId,
        String gameName
) {
}
