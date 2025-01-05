package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

public record GameStockChangedMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {
}
