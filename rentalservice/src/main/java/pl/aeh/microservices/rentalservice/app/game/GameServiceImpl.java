package pl.aeh.microservices.rentalservice.app.game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.rentalservice.messaging.GameCreatedMessage;
import pl.aeh.microservices.rentalservice.messaging.GameRemovedMessage;
import pl.aeh.microservices.rentalservice.messaging.GameStockChangedMessage;
import pl.aeh.microservices.rentalservice.messaging.GameUpdatedMessage;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public GameDto getGame(UUID gameId) {
        return gameRepository.findById(gameId)
                .map(GameEntity::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Gra o id %s nie istnieje", gameId)));
    }

    @Override
    public void addGame(GameCreatedMessage game) {
        log.info("Dodawanie gry {}...", game.id());
        GameEntity entity = new GameEntity(game.id(), game.gameName(), 0);
        gameRepository.save(entity);
    }

    @Override
    public void updateGame(GameUpdatedMessage message) {
        log.info("Aktualizacja gry {}...", message.id());
        try {
            GameEntity entity = getGameEntity(message.id());
            entity.changeName(message.gameName());
            gameRepository.save(entity);
            log.info("Gra {} zaktualizowana", message.id());
        } catch (EntityNotFoundException e) {
            log.info("Brak gry {}", message.id());
        }
    }

    @Override
    public void removeGame(GameRemovedMessage game) {
        log.info("Usuwanie gry {}...", game.gameId());
        try {
            GameEntity entity = getGameEntity(game.gameId());
            gameRepository.delete(entity);
            log.info("Gra {} usunięta", game.gameId());
        } catch (EntityNotFoundException e) {
            log.warn("Gra o id {} nie istnieje, nie można usunąć", game.gameId());
        }
    }

    @Override
    public void updateGameQuantity(GameStockChangedMessage message) {
        GameEntity gameEntity = gameRepository.findById(message.gameId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Gra o id %s nie istnieje", message.gameId())));
        gameEntity.changeQuantity(message.quantity());
        gameRepository.save(gameEntity);
    }

    private GameEntity getGameEntity(UUID gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Gra o id %s nie istnieje", gameId)));
    }
}
