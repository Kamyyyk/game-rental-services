package pl.aeh.microservices.reviewservice.app.review;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getReviewsByGameId(UUID gameId) {
        return reviewRepository.findByGameId(gameId)
                .stream()
                .map(ReviewEntity::toDto)
                .toList();
    }

    @Override
    public Optional<ReviewDto> getReview(UUID reviewId) {
        return reviewRepository.findById(reviewId).map(ReviewEntity::toDto);
    }

    @Override
    public double getAverageRating(UUID gameId) {
        List<ReviewEntity> reviews = reviewRepository.findByGameId(gameId);
        return reviews.stream()
                .mapToInt(ReviewEntity::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public void addReview(ReviewDto review) {
        ReviewEntity entity = new ReviewEntity(review.id(), review.gameId(), review.gameName(), review.content(), review.rating());
        reviewRepository.save(entity);
        log.info("Dodano recenzję do gry o id: {}.", review.gameId());
    }

    @Override
    public void updateReview(ReviewDto review) {
        ReviewEntity entity = reviewRepository.findById(review.id())
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono recenzji do gry o id: {}", review.id()));
        entity.content = review.content();
        entity.rating = review.rating();
        reviewRepository.save(entity);
        log.info("Recenzja o id {} została zmodyfikowana.", review.id());
    }

    @Override
    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
        log.info("Recenzja o id {} została usunięta.", review.id());
    }
}