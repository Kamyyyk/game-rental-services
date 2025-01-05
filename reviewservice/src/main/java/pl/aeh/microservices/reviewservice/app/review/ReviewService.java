package pl.aeh.microservices.reviewservice.app.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.aeh.microservices.reviewservice.messaging.GameCreatedMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewService {
    List<ReviewDto> getReviewsById(UUID gameId);

    Optional<ReviewDto> getReview(UUID reviewId);

    double getAverageRating(UUID gameId);

    void addReview(ReviewDto review);

    void updateReview(ReviewDto review);

    void deleteReview(UUID reviewId);

    List<ReviewDto> getReviewsByGameId(UUID gameId);

    ReviewDto getReviewById(UUID reviewId);

    List<ReviewDto> findAll();

    void addGame(GameCreatedMessage gameCreatedMessage);
}