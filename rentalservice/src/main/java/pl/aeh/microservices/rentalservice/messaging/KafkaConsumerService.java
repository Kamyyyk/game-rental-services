package pl.aeh.microservices.rentalservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-removed", groupId = "game-rental-group")
    void listenGameRemovedMessage(String message);

    @KafkaListener(topics = "game-created", groupId = "game-rental-group")
    void listenGameCreatedMessage(String message);

    @KafkaListener(topics = "game-updated", groupId = "game-rental-group")
    void listenGameUpdatedMessage(String message);

    @KafkaListener(topics = "game-stock-changed", groupId = "game-rental-group")
    void listenGameStockChanged(String message);

}
