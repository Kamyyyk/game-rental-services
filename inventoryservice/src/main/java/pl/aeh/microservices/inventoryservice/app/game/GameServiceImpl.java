package pl.aeh.microservices.inventoryservice.app.game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.inventoryservice.messaging.*;

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
        GameEntity entity = new GameEntity(game.gameId(), game.gameName());
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

    private GameEntity getGameEntity(UUID gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Gra o id %s nie istnieje", gameId)));
    }
}
