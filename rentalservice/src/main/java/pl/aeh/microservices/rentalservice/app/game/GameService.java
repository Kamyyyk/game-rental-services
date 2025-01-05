package pl.aeh.microservices.rentalservice.app.game;

import pl.aeh.microservices.rentalservice.messaging.GameCreatedMessage;
import pl.aeh.microservices.rentalservice.messaging.GameRemovedMessage;
import pl.aeh.microservices.rentalservice.messaging.GameStockChangedMessage;
import pl.aeh.microservices.rentalservice.messaging.GameUpdatedMessage;

import java.util.UUID;

public interface GameService {
    GameDto getGame(UUID gameId);

    void addGame(GameCreatedMessage game);

    void updateGame(GameUpdatedMessage message);

    void removeGame(GameRemovedMessage gameId);

    void updateGameQuantity(GameStockChangedMessage message);
}
