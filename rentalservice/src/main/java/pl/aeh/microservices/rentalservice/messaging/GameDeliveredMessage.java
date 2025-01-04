package pl.aeh.microservices.rentalservice.messaging;

import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

public record GameDeliveredMessage(
            UUID id,
            UUID gameId,
            Integer quantity
    )
{


}
