package pl.aeh.microservices.inventoryservice.app.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    List<ReviewEntity> findByGameId(UUID gameId);
}