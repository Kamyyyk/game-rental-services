package pl.aeh.microservices.gameservice.app.game;
import lombok.Getter;
import pl.aeh.microservices.gameservice.GameGenre.GameGenre;

import java.util.UUID;

@Getter
public class Game {
    private UUID game_id;
    private String title;
    private String description;
//    private GameEntity type_id;
//    private GameGenre genre_id;
    private int players_from;
    private int players_to;
    private int age_from;
    private int age_to;
    private boolean available;
    private int averageRate;
    private int totalReviews;
    private int quantity;
}
