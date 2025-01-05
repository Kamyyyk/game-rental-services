package pl.aeh.microservices.gameservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import pl.aeh.microservices.gameservice.app.game.GameDto;

public interface KafkaConsumerService {
    @KafkaListener(topics = "game-stock-changed", groupId = "game-group")
    void listenStockUpdated(GameStockChangedMessage message);
}
