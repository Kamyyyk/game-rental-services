package pl.aeh.microservices.gameservice.app.game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
    @Column(name = "players_from")
    private int playersFrom;
    @Column(name = "players_to")
    private int playersTo;
    @Column(name = "age_from")
    private int ageFrom;
    @Column(name = "age_to")
    private int ageTo;
    @Column(name = "available")
    private boolean available;
    @Column(name = "average_rate")
    private double averageRate;
    @Column(name = "total_reviews")
    private int totalReviews;

    public GameEntity() {
    }

    public GameEntity(InputGameDto game) {
        if (game.id() == null) {
            this.id = UUID.randomUUID();
            log.info("Created GameEntity with id: {}", this.id);
        }
        this.title = game.title();
        this.description = game.description();
        this.playersFrom = game.playersFrom();
        this.playersTo = game.playersTo();
        this.ageFrom = game.ageFrom();
        this.ageTo = game.ageTo();
        this.available = false;
        this.averageRate = 0;
        this.totalReviews = 0;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeAvailable(boolean available) {
        this.available = available;
    }

    public GameDto toDto() {
        return new GameDto(
                this.getId(),
                this.getTitle(),
                this.getDescription(),
                this.getPlayersFrom(),
                this.getPlayersTo(),
                this.getAgeFrom(),
                this.getAgeTo(),
                this.isAvailable(),
                this.getAverageRate(),
                this.getTotalReviews()
        );
    }

    public void updateReviewsData(Integer totalReviews, Double averageRate) {
        this.totalReviews = totalReviews;
        this.averageRate = averageRate.intValue();
    }
}
