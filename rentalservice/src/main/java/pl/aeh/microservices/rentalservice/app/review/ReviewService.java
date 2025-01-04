package pl.aeh.microservices.inventoryservice.app.review;

import java.util.UUID;

public interface ReviewService {
    List<ReviewDto> getReviewsById(UUID gameId);

    Optional<ReviewDto> getReview(UUID reviewId);
    double getAverageRating(UUID gameId);

    void addReview(ReviewDto review);
    void updateReview(ReviewDto review);
    void deleteReview(UUID reviewId);
}