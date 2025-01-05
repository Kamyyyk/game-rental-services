package pl.aeh.microservices.inventoryservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;
import pl.aeh.microservices.inventoryservice.app.game.GameService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final GameService gameService;
    private final ObjectMapper objectMapper;

    @Override
    public void listenGameCreatedMessage(String message) {
        gameService.addGame(readValue(message, GameCreatedMessage.class));
    }

    @Override
    public void listenGameUpdatedMessage(GameDto message) {
        gameService.updateGame(message);
    }

    @Override
    public void listenGameRemovedMessage(UUID message) {
        gameService.removeGame(message);
    }

    public <T> T readValue(String message, Class<T> clazz) {
        try {
            return objectMapper.readValue(message, clazz);
        } catch (Exception e) {
            log.error("Cannot read value of class {} from message: {}", clazz.getName(), message, e);
            return null;
        }
    }
}
