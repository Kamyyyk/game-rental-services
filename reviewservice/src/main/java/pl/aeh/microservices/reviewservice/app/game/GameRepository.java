package pl.aeh.microservices.reviewservice.app.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
