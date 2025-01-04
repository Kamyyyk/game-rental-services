package pl.aeh.microservices.rentalservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import pl.aeh.microservices.rentalservice.app.Game.GameDto;

import java.util.UUID;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-removed", groupId = "game-rental-group")
    void listenGameRemovedMessage(UUID message);

    @KafkaListener(topics = "game-created", groupId = "game-rental-group")
    void listenGameCreatedMessage(GameDto message);

    @KafkaListener(topics = "game-updated", groupId = "game-rental-group")
    void listenGameUpdatedMessage(GameDto message);

    @KafkaListener(topics = "game-stock-changed", groupId = "game-rental-group")
    void listenGameStockChanged(GameDeliveredMessage message);

}
