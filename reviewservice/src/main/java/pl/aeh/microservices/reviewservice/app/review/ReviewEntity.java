package pl.aeh.microservices.reviewservice.app.review;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
class ReviewEntity {
    @Id
    private UUID id;
    private UUID gameId;
    private String gameName;
    private String content;
    private int rating;

    public ReviewEntity() { }

    public ReviewEntity(UUID id, UUID gameId, String gameName, String content, int rating) {
        this.id = id;
        this.gameId = gameId;
        this.gameName = gameName;
        this.content = content;
        this.rating = rating;
    }

    public ReviewDto toDto() {
        return new ReviewDto(id, gameId, gameName, content, rating);
    }
}