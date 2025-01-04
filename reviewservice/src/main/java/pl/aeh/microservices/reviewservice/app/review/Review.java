package pl.aeh.microservices.reviewservice.app.review;

import lombok.Getter;

import java.util.UUID;

@Getter
class Review {
    private UUID id;
    private UUID gameId;
    private String gameName;
    private String content;
    private Integer rating;
}