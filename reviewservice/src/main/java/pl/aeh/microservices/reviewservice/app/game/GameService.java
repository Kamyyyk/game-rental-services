package pl.aeh.microservices.reviewservice.app.game;

import pl.aeh.microservices.reviewservice.messaging.GameUpdatedMessage;

import java.util.UUID;

public interface GameService {
    void addGame(GameDto game);

    void removeGame(UUID gameId);

    void renameGame(GameUpdatedMessage message);
}
