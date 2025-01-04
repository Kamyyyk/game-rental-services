package pl.aeh.microservices.gameservice.app.game;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.gameservice.messaging.KafkaProducerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameService gameService;
    private final KafkaProducerService kafkaProducerService;

    public GameDto getGame(UUID gameId) {
        return gameService.getGame(gameId);
    }

    public void addGame(GameDto game) {
        log.info("Adding game {}", game.id());
        GameEntity entity = new GameEntity(game);
        gameRepository.save(entity);
        kafkaProducerService.gameCreated(game);
    }

    public void editGame(GameDto game) {
        log.info("Updating game {}", game.id());
        try {
            GameEntity entity = getGameEntity(game.id());
            entity.changeTitle(game.title());
            entity.changeDescription(game.description());
            kafkaProducerService.gameUpdated(game);
        } catch (Exception e) {
            log.warn("Can't update game with id {}, game does not exist", game.id());
        }
    }

    public void removeGame(UUID gameId) {
        log.info("Removing game {}", gameId);
        try {
            GameEntity entity = getGameEntity(gameId);
            gameRepository.delete(entity);
            log.info("Removed game {}", gameId);
            kafkaProducerService.gameRemoved(gameId);
        } catch (Exception e) {
            log.warn("Can't remove game with id {}, game does not exist", gameId);
        }
    }

    public void checkAvailability(UUID gameId) {
        log.info("Checking availability of game {}", gameId);
        try {
            GameEntity entity = getGameEntity(gameId);
            if (entity.isAvailable()) {
                log.info("Game with id {} is available", gameId);
            }
        } catch (Exception e) {
            log.warn("Game with id {} does on exist ", gameId);
        }
    }

    public void editGameAvailability(GameDto game, boolean isGameAvailable) {
        log.info("Changing game availability {}", game.id());
        try {
            GameEntity entity = getGameEntity(game.id());
            entity.changeAvailable(isGameAvailable);
        } catch (Exception e) {
            log.warn("Can't update game availability with id {}, game does not exist", game.id());
        }
    }

//    @Override
//    public Page<GameDto> findGames(GameSearchParameters parameters, Pageable pageable) {
//        Page<GameEntity> games = gameRepository.findAll(parameters, pageable);
//        return games.map(e -> {
//            Game game = new Game(e);
//            GameDto gameDto = gameService.getGame(e.getId());
//            return new GameDto(gameDto.id())
//        });
//    }

    private GameEntity getGameEntity(UUID gameId) {
        return gameRepository.findById(gameId).orElseThrow(null);
    }
}
