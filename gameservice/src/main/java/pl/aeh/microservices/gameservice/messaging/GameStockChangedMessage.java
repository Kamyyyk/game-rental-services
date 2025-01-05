package pl.aeh.microservices.gameservice.messaging;

import java.util.UUID;

public record GameStockChangedMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {
}
