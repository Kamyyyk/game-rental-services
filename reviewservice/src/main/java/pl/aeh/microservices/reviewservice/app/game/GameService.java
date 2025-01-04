package pl.aeh.microservices.reviewservice.app.game;

import java.util.UUID;

public interface GameService {
    GameDto getGame(UUID gameId);
    void addGame(GameDto game);
    void updateGame(GameDto message);
    void removeGame(UUID gameId);
    void renameGame(GameDto message);
}
