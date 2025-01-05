package pl.aeh.microservices.rentalservice.messaging;

import java.util.UUID;

public interface KafkaProducerService {

    void gameOrdered(UUID gameId);

    void gameReturned(UUID gameId);

}
