package pl.aeh.microservices.gameservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.gameservice.app.game.GameDto;
import pl.aeh.microservices.gameservice.app.game.GameService;

@Service
@RequiredArgsConstructor
class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final GameService gameService;

    @Override
    public void listenStockUpdated(GameDto game, Integer quantity) {
        gameService.editGameAvailability(game, quantity > 0);
    }
}