package pl.aeh.microservices.reviewservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaConsumerService {

    @KafkaListener(topics = "game-created", groupId = "game-review-group")
    void listenGameCreatedMessage(String message);

    @KafkaListener(topics = "game-updated", groupId = "game-review-group")
    void listenGameUpdatedMessage(String message);

    @KafkaListener(topics = "game-removed", groupId = "game-review-group")
    void listenGameRemovedMessage(String message);
}