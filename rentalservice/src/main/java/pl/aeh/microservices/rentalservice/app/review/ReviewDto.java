package pl.aeh.microservices.inventoryservice.app.review;

import pl.aeh.microservices.inventoryservice.app.game.ReviewDto;

import java.util.UUID;

public record ReviewDto(
        UUID id,
        UUID gameId,
        String gameName;
        String content,
        Integer rating
) {
}