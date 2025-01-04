package pl.aeh.microservices.gameservice.messaging;

import pl.aeh.microservices.gameservice.app.game.GameDto;

import java.util.UUID;

public interface KafkaProducerService {
    void gameCreated(GameDto game);
    void gameRemoved(UUID gameId);
    void gameUpdated(GameDto game);
}
