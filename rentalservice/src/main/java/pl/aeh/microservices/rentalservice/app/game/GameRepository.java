package pl.aeh.microservices.rentalservice.app.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
