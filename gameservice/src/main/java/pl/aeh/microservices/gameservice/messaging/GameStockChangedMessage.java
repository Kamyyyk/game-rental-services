package pl.aeh.microservices.gameservice.messaging;

import java.util.UUID;

record GameStockChangedMessage(
        UUID id,
        UUID gameId,
        Integer quantity
) {

}
