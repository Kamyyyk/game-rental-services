package pl.aeh.microservices.reviewservice.app.game;

import java.util.UUID;

public interface GameService {
    void addGame(GameDto game);

    void removeGame(UUID gameId);

    void renameGame(GameDto message);
}
