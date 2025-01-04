package pl.aeh.microservices.inventoryservice.app.game;

import java.util.UUID;

public interface GameService {
    GameDto getGame(UUID gameId);
    void addGame(GameDto game);
    void updateGame(GameDto message);
    void removeGame(UUID gameId);
}
