package pl.aeh.microservices.inventoryservice.messaging;

import java.util.UUID;

public record GameUpdatedMessage(
        UUID id,
        UUID gameId,
        String gameName
) {
}
