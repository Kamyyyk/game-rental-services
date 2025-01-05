package pl.aeh.microservices.reviewservice.app.review;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.reviewservice.app.game.GameDto;
import pl.aeh.microservices.reviewservice.app.game.GameService;
import pl.aeh.microservices.reviewservice.messaging.GameCreatedMessage;
import pl.aeh.microservices.reviewservice.messaging.GameRemovedMessage;
import pl.aeh.microservices.reviewservice.messaging.GameUpdatedMessage;
import pl.aeh.microservices.reviewservice.messaging.KafkaProducerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final KafkaProducerService kafkaProducerService;
    private final GameService gameService;

    @Override
    public List<ReviewDto> getReviewsByGameId(UUID gameId) {
        return reviewRepository.findByGameId(gameId)
                .stream()
                .map(ReviewEntity::toDto)
                .toList();
    }

    @Override
    public ReviewDto getReviewById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .map(ReviewEntity::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ReviewDto> findAll() {
        return reviewRepository.findAll().stream()
                .map(ReviewEntity::toDto)
                .toList();
    }

    @Override
    public void addGame(GameCreatedMessage gameCreatedMessage) {
        gameService.addGame(new GameDto(
                gameCreatedMessage.gameId(),
                gameCreatedMessage.gameName()
        ));
    }

    @Override
    public void removeGame(GameRemovedMessage gameRemovedMessage) {
        gameService.removeGame(gameRemovedMessage.gameId());
    }

    @Override
    public void renameGame(GameUpdatedMessage gameUpdatedMessage) {
        gameService.renameGame(gameUpdatedMessage);
    }

    @Override
    public List<ReviewDto> getReviewsById(UUID reviewId) {
        return reviewRepository.findAllByGameId(reviewId);
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
        kafkaProducerService.reviewChanged(review);
        log.info("Dodano recenzję do gry o id: {}.", review.gameId());
    }

    @Override
    public void updateReview(ReviewDto review) {
        ReviewEntity entity = reviewRepository.findById(review.id())
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono recenzji do gry o id: " + review.id()));
        entity.setContent(review.content());
        entity.setRating(review.rating());
        reviewRepository.save(entity);
        kafkaProducerService.reviewChanged(review);
        log.info("Recenzja o id {} została zmodyfikowana.", review.id());
    }

    @Override
    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
        log.info("Recenzja o id {} została usunięta.", reviewId);
        kafkaProducerService.reviewChanged(reviewRepository.findById(reviewId)
                .map(ReviewEntity::toDto)
                .orElseThrow(EntityNotFoundException::new)
        );
    }
}
