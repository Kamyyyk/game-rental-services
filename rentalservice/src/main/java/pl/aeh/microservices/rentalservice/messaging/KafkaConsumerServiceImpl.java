package pl.aeh.microservices.rentalservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.rentalservice.app.Game.GameDto;
import pl.aeh.microservices.rentalservice.app.Game.GameService;
import pl.aeh.microservices.rentalservice.messaging.KafkaConsumerService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final GameService gameService;

    @KafkaListener(topics = "game-created", groupId = "game-group")
    @Override
    public void listenGameCreatedMessage(GameDto message) {
        gameService.addGame(message);
    }

    @KafkaListener(topics = "game-updated", groupId = "game-group")
    @Override
    public void listenGameUpdatedMessage(GameDto message) {
        gameService.updateGame(message);
    }

    @KafkaListener(topics = "game-removed", groupId = "game-group")
    @Override
    public void listenGameRemovedMessage(UUID message) {
        gameService.removeGame(message);
    }

    @KafkaListener(topics = "game-stock-changed", groupId = "game-group")
    @Override
    public void listenGameStockChanged(GameDeliveredMessage message) {
        gameService.updateGameQuantity(message);
    }

    //Dodania stocku
    //zmiana stocku
}
