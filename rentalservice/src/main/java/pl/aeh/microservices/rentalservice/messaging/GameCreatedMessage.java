package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

public record GameCreatedMessage(
        UUID id,
        UUID gameId,
        String gameName
) {
}
