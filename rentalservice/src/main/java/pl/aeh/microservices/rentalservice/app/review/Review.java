package pl.aeh.microservices.inventoryservice.app.review;

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