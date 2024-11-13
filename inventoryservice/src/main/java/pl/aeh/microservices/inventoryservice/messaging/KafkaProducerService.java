package pl.aeh.microservices.inventoryservice.messaging;

import pl.aeh.microservices.inventoryservice.app.game.GameDto;

public interface KafkaProducerService {

    void stockChanged(GameDto game, Integer quantity);

}
