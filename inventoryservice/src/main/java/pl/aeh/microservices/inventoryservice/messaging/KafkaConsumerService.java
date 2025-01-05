package pl.aeh.microservices.inventoryservice.messaging;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-created", groupId = "game-inventory-group")
    void listenGameCreatedMessage(String message);

    @KafkaListener(topics = "game-updated", groupId = "game-inventory-group")
    void listenGameUpdatedMessage(String message);

    @KafkaListener(topics = "game-removed", groupId = "game-inventory-group")
    void listenGameRemovedMessage(String message);

    @KafkaListener(topics = "game-ordered", groupId = "game-inventory-group")
    void listenGameOrderedMessage(String message);

    @KafkaListener(topics = "game-returned", groupId = "game-inventory-group")
    void listenGameReturnedMessage(String message);
}
