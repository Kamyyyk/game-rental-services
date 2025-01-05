package pl.aeh.microservices.inventoryservice.app.stock;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;
import pl.aeh.microservices.inventoryservice.app.game.GameService;
import pl.aeh.microservices.inventoryservice.messaging.GameOrderedMessage;
import pl.aeh.microservices.inventoryservice.messaging.GameReturnedMessage;
import pl.aeh.microservices.inventoryservice.messaging.KafkaProducerService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class GameStockServiceImpl implements GameStockService {

    private final GameStockRepository gameStockRepository;
    private final GameService gameService;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public Page<GameStockDto> findAllByParameters(GameStockSearchParameters parameters, Pageable pageable) {
        log.info("Wyszukiwanie gry na podstawie kryteriów: {}", parameters.toString());
        Page<GameStockEntity> gameStocks = gameStockRepository.findAll(parameters.toSpecification(), pageable);
        return gameStocks.map(e -> {
            GameStock gameStock = new GameStock(e);
            GameDto gameDto = gameService.getGame(e.getId());
            return new GameStockDto(gameDto, gameStock.getQuantity());
        });
    }

    @Override
    public GameStockDto getGameStockByGameId(UUID gameId) {
        log.info("Wyszukiwanie stanu gry o id: {}", gameId);
        GameDto game = gameService.getGame(gameId);
        GameStock gameStock = getGameStockEntity(gameId);
        return new GameStockDto(game, gameStock.getQuantity());
    }

    @Override
    public void receiveGameDelivery(UUID gameId, Integer quantity) {
        log.info("Rozpoczęto przyjęcie dostawy gry {} w ilości {} szt.", gameId, quantity);

        GameDto game = gameService.getGame(gameId);
        GameStock gameStock = getGameStockEntity(gameId);

        gameStock.receiveDelivery(quantity);
        gameStockRepository.save(gameStock.getEntity());

        log.info("Przyjęcie dostawy gry {} w ilości {} szt. zakończone poprawnie.", gameId, quantity);
        kafkaProducerService.stockChanged(game, quantity);
    }

    @Override
    public void registerGameLoss(UUID gameId, Integer quantity) {
        log.info("Rozpoczęto rejestrację straty gry o id {} w ilości {} szt.", gameId, quantity);

        GameDto game = gameService.getGame(gameId);
        GameStock gameStock = getGameStockEntity(gameId);

        gameStock.registerLoss(quantity);
        gameStockRepository.save(gameStock.getEntity());

        log.info("Rejestracja straty gry {} w ilości {} szt. zakończona poprawnie", game.name(), quantity);
        kafkaProducerService.stockChanged(game, quantity);
    }

    @Override
    public void orderGame(GameOrderedMessage order) {
       registerGameLoss(order.gameId(), 1);
    }

    @Override
    public void returnGame(GameReturnedMessage game) {
        receiveGameDelivery(game.gameId(), 1);
    }

    private GameStock getGameStockEntity(UUID gameId) {
        return gameStockRepository.findById(gameId)
                .map(GameStock::new)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Gra o id %s nie istnieje", gameId)));
    }
}
