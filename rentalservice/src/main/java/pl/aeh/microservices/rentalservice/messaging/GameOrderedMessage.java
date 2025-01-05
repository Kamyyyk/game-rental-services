package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

public record GameOrderedMessage(
        UUID id,
        UUID gameId) {
}
