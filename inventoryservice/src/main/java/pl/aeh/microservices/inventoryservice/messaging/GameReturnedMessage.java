package pl.aeh.microservices.inventoryservice.messaging;

import java.util.UUID;

public record GameReturnedMessage(
        UUID id,
        UUID gameId
) {
}
