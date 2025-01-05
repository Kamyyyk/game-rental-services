package pl.aeh.microservices.gameservice.app.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.aeh.microservices.gameservice.app.game.InputGameDto;
import pl.aeh.microservices.gameservice.app.game.GameDto;
import pl.aeh.microservices.gameservice.app.game.GameService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @GetMapping("/get")
    public GameDto getGame(@RequestParam UUID gameId) {return gameService.getGame(gameId);}

    @PostMapping("/add")
    public void addGame(@RequestBody InputGameDto inputGameDto) {
        gameService.addGame(inputGameDto);
    }

    @PutMapping("/edit")
    public void editGame(@RequestBody InputGameDto inputGameDto) {
        gameService.editGame(inputGameDto);
    }

    @GetMapping("/check-availability")
    public boolean checkAvailability(@RequestParam UUID gameId) {
        return gameService.checkAvailability(gameId);
    }

    @GetMapping("/list")
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @DeleteMapping("/remove")
    public void removeGame(@RequestParam UUID gameId) {
        gameService.removeGame(gameId);
    }

}
