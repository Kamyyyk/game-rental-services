package pl.aeh.microservices.reviewservice.app.review;

import java.util.UUID;

public record ReviewDto(
        UUID id,
        UUID gameId,
        String gameName,
        String content,
        Integer rating
) {
}