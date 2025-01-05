package pl.aeh.microservices.reviewservice.app.game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public void addGame(GameDto game) {
        log.info("Dodawanie gry {}...", game.id());
        GameEntity entity = new GameEntity(game.id(), game.name());
        gameRepository.save(entity);
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
    public void renameGame(GameDto message) {
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

    private GameEntity getGameEntity(UUID gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Gra o id %s nie istnieje", gameId)));
    }
}
