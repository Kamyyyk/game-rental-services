package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

public record GameReturnedMessage(
        UUID id,
        UUID gameId
) {
}
