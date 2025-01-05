package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

public record GameDeliveredMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {


}
