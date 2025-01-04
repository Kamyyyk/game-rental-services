package pl.aeh.microservices.gameservice.app.game;

import pl.aeh.microservices.gameservice.GameType.GameType;

import java.util.UUID;

public class GameSearchParameters {
    private UUID gameId;
    private String title;
    private GameType gameType;
}
