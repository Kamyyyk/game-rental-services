package pl.aeh.microservices.inventoryservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.inventoryservice.app.game.GameService;
import pl.aeh.microservices.inventoryservice.app.stock.GameStockService;

@Slf4j
@Service
@RequiredArgsConstructor
class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final GameService gameService;
    private final GameStockService gameStockService;
    private final ObjectMapper objectMapper;

    @Override
    public void listenGameCreatedMessage(String message) {
        gameService.addGame(readValue(message, GameCreatedMessage.class));
    }

    @Override
    public void listenGameUpdatedMessage(String message) {
        gameService.updateGame(readValue(message, GameUpdatedMessage.class));
    }

    @Override
    public void listenGameRemovedMessage(String message) {
        gameService.removeGame(readValue(message, GameRemovedMessage.class));
    }

    @Override
    public void listenGameOrderedMessage(String message) {
        gameStockService.orderGame(readValue(message, GameOrderedMessage.class));
    }

    @Override
    public void listenGameReturnedMessage(String message) {
        gameStockService.returnGame(readValue(message, GameReturnedMessage.class));
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
