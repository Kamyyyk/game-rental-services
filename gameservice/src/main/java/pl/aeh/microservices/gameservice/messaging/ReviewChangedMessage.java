package pl.aeh.microservices.gameservice.messaging;

import java.util.UUID;

public record ReviewChangedMessage(
        UUID id,
        UUID gameId,
        Integer totalReviews,
        Double averageRating
) {
}
