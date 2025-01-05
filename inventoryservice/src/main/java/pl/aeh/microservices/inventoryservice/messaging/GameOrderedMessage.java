package pl.aeh.microservices.inventoryservice.messaging;

import java.util.UUID;

public record GameOrderedMessage(
        UUID id,
        UUID gameId) {
}
