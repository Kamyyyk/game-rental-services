package pl.aeh.microservices.rentalservice.app.RentalOrder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.rentalservice.app.Game.GameDto;
import pl.aeh.microservices.rentalservice.app.Game.GameService;
import pl.aeh.microservices.rentalservice.messaging.KafkaConsumerService;
import pl.aeh.microservices.rentalservice.messaging.KafkaProducerService;

import java.time.LocalDateTime;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
class RentalOrderServiceImpl implements RentalOrderService {

    private final RentalOrderRepository rentalOrderRepository;
    private final GameService gameService;
    private final KafkaConsumerService KafkaConsumerService;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public void createRentalOrder(UUID game_id) {
        GameDto game = gameService.getGame(game_id);
        if (game.quantity() > 0) {
            RentalOrderEntity rentalOrderEntity = new RentalOrderEntity(UUID.randomUUID(), LocalDateTime.now(), game_id, null);
            rentalOrderRepository.save(rentalOrderEntity);
            kafkaProducerService.gameOrdered(game_id);
        } else {
            throw new IllegalArgumentException("Game quantity must be greater than 0");
        }
    }

    @Override
    public void gameReturn(UUID id) {
        RentalOrderEntity order = rentalOrderRepository.getReferenceById(id);
        order.returnOrder();
        rentalOrderRepository.save(order);
        kafkaProducerService.gameReturned(order.getGameid());
    }

}
