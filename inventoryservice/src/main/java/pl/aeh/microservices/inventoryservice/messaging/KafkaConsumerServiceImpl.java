package pl.aeh.microservices.inventoryservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Override
    public void listenGameCreatedMessage(String message) {
        GameCreatedMessage gameCreatedMessage = objectMapper.convertValue(message, GameCreatedMessage.class);
        gameService.addGame(gameCreatedMessage);
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
