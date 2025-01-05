package pl.aeh.microservices.reviewservice.app.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    List<ReviewEntity> findByGameId(UUID gameId);

    List<ReviewDto> findAllByGameId(UUID reviewId);
}