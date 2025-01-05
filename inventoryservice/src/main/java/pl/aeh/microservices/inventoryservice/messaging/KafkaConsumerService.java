package pl.aeh.microservices.inventoryservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-created", groupId = "game-inventory-group")
    void listenGameCreatedMessage(GameCreatedMessage message);

    @KafkaListener(topics = "game-updated", groupId = "game-inventory-group")
    void listenGameUpdatedMessage(GameDto message);

    @KafkaListener(topics = "game-removed", groupId = "game-inventory-group")
    void listenGameRemovedMessage(UUID message);
}
