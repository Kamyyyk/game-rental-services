package pl.aeh.microservices.inventoryservice.messaging;

import java.util.UUID;

public record GameCreatedMessage(
        UUID id,
        UUID gameId,
        String gameName
) {
}
