package pl.aeh.microservices.reviewservice.messaging;

import java.util.UUID;

public record GameUpdatedMessage(
        UUID id,
        UUID gameId,
        String gameName
) {
}
