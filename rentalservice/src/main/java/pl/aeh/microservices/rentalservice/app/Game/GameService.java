package pl.aeh.microservices.rentalservice.app.Game;

import java.util.UUID;

public interface GameService {
    GameDto getGame(UUID gameId);
    void addGame(GameDto game);
    void updateGame(GameDto message);
    void removeGame(UUID gameId);
}
