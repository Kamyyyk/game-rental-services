package pl.aeh.microservices.inventoryservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-created", groupId = "game-group")
    void listenGameCreatedMessage(GameDto message);

    @KafkaListener(topics = "game-updated", groupId = "game-group")
    void listenGameUpdatedMessage(GameDto message);

    @KafkaListener(topics = "game-removed", groupId = "game-group")
    void listenGameRemovedMessage(UUID message);
}
