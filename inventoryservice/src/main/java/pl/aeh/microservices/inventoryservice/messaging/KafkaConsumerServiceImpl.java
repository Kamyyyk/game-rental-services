package pl.aeh.microservices.inventoryservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;
import pl.aeh.microservices.inventoryservice.app.game.GameService;

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
}
