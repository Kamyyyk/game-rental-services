package pl.aeh.microservices.reviewservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import pl.aeh.microservices.reviewservice.app.game.GameDto;

import java.util.UUID;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-created", groupId = "game-review-group")
    void listenGameCreatedMessage(GameDto message);

    @KafkaListener(topics = "game-removed", groupId = "game-review-group")
    void listenGameRemovedMessage(UUID message);

    @KafkaListener(topics = "game-updated", groupId = "game-review-group")
    void listenGameUpdatedMessage(GameDto message);
}