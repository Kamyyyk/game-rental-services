package pl.aeh.microservices.rentalservice.messaging;

import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

public interface KafkaProducerService {

    void gameOrdered(UUID gameId);
    void gameReturned(UUID gameId);

}
