package pl.aeh.microservices.rentalservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.rentalservice.app.Game.GameDto;
import pl.aeh.microservices.rentalservice.app.Game.GameService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final GameService gameService;

    @Override
    public void listenGameCreatedMessage(GameDto message) {
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

    @Override
    public void listenGameStockChanged(GameDeliveredMessage message) {
        gameService.updateGameQuantity(message);
    }
}
