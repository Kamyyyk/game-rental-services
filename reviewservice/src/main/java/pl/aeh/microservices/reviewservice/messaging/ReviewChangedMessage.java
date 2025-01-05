package pl.aeh.microservices.reviewservice.messaging;

import java.util.UUID;

public record ReviewChangedMessage(
        UUID id,
        UUID gameId,
        Integer totalReviews,
        Double averageRating
) {
}
