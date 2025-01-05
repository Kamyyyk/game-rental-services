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

    @Override
    public void listenGameCreatedMessage(GameCreatedMessage message) {
        gameService.addGame(message);
    }

    @Override
    public void listenGameUpdatedMessage(GameDto message) {
        gameService.updateGame(message);
    }

    @Override
    public void listenGameRemovedMessage(UUID message) {
        gameService.removeGame(message);
    }
}
