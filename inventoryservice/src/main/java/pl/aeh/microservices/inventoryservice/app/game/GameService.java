package pl.aeh.microservices.inventoryservice.app.game;

import pl.aeh.microservices.inventoryservice.messaging.GameCreatedMessage;

import java.util.UUID;

public interface GameService {
    GameDto getGame(UUID gameId);
    void addGame(GameCreatedMessage game);
    void updateGame(GameDto message);
    void removeGame(UUID gameId);
}
