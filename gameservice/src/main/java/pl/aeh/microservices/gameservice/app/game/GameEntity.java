package pl.aeh.microservices.gameservice.app.game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.aeh.microservices.gameservice.GameGenre.GameGenreEntity;
import pl.aeh.microservices.gameservice.GameType.GameTypeEntity;

import java.util.UUID;

@Slf4j
@Entity
@Getter
@Table(name = "game")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
//    @ManyToOne
//    @JoinColumn(name = "game_type_id")
//    private GameTypeEntity gameType;
//    @ManyToOne
//    @JoinColumn(name = "game_genre_id")
//    private GameGenreEntity gameGenre;
    @Column(name = "players_from")
    private int players_from;
    @Column(name = "players_to")
    private int players_to;
    @Column(name = "age_from")
    private int age_from;
    @Column(name = "age_to")
    private int age_to;
    @Column(name = "available")
    private boolean available;
    @Column(name = "averageRate")
    private int averageRate;
    @Column(name = "totalReviews")
    private int totalReviews;

    public GameEntity() {}

    public GameEntity(GameDto game) {
        if (game.id() == null) {
            this.id = UUID.randomUUID();
            log.info("Created GameEntity with id: {}", this.id);
        }
        this.title = game.title();
        this.description = game.description();
//        this.gameType = game.type_id();
//        this.gameGenre = game.genre_id();
        this.players_from = game.players_from();
        this.players_to = game.players_to();
        this.age_from = game.age_from();
        this.age_to = game.age_to();
        this.available = game.available();
        this.averageRate = game.averageRate();
        this.totalReviews = game.totalReviews();
    }

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeDescription(String description) {
        this.description = description;
    }

//    public void changeGameType(GameTypeEntity gameType) {
//        this.gameType = gameType;
//    }

//    public void changeGameGenre(GameGenreEntity gameGenre) {
//        this.gameGenre = gameGenre;
//    }

    public void changePlayersFrom(int players_from) {
        this.players_from = players_from;
    }

    public void changePlayersTo(int players_to) {
        this.players_to = players_to;
    }

    public void changeAgeFrom(int age_from) {
        this.age_from = age_from;
    }

    public void changeAgeTo(int age_to) {
        this.age_to = age_to;
    }

    public void changeAvailable(boolean available) {
        this.available = available;
    }

    public void changeAverageRate(int averageRate) {
        this.averageRate = averageRate;
    }

    public void changeTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public GameDto toDto() {
        return new GameDto(
                this.getId(),
                this.getTitle(),
                this.getDescription(),
                this.getPlayers_from(),
                this.getPlayers_to(),
                this.getAge_from(),
                this.getAge_to(),
                this.isAvailable(),
                this.getAverageRate(),
                this.getTotalReviews()
        );
    }
}
