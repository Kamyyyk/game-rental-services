package pl.aeh.microservices.rentalservice.app.Game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.rentalservice.messaging.GameDeliveredMessage;

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
    public void addGame(GameDto game) {
        log.info("Dodawanie gry {}...", game.id());
        GameEntity entity = new GameEntity(game.id(), game.name(), game.quantity());
        gameRepository.save(entity);
    }

    @Override
    public void updateGame(GameDto message) {
        log.info("Aktualizacja gry {}...", message.id());
        try {
            GameEntity entity = getGameEntity(message.id());
            entity.changeName(message.name());
            gameRepository.save(entity);
            log.info("Gra {} zaktualizowana", message.id());
        } catch (EntityNotFoundException e) {
            log.info("Brak gry {}", message.id());
            addGame(message);
        }
    }

    @Override
    public void removeGame(UUID gameId) {
        log.info("Usuwanie gry {}...", gameId);
        try {
            GameEntity entity = getGameEntity(gameId);
            gameRepository.delete(entity);
            log.info("Gra {} usunięta", gameId);
        } catch (EntityNotFoundException e) {
            log.warn("Gra o id {} nie istnieje, nie można usunąć", gameId);
        }
    }

    @Override
    public void updateGameQuantity(GameDeliveredMessage message) {
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
