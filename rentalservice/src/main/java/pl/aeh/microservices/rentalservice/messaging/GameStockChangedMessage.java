package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

record GameStockChangedMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {
}
