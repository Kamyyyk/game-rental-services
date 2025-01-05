package pl.aeh.microservices.gameservice.app.presentation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.aeh.microservices.gameservice.app.game.GameDto;
import pl.aeh.microservices.gameservice.app.game.GameSearchParameters;
import pl.aeh.microservices.gameservice.app.game.GameService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @GetMapping("/get")
    public GameDto getGame(@RequestParam UUID gameId) {return gameService.getGame(gameId);}

    @PostMapping("/add")
    public void addGame(@RequestBody GameDto gameDto) {
        gameService.addGame(gameDto);
    }

    @PutMapping("/edit")
    public void editGame(@RequestBody GameDto gameDto) {
        gameService.editGame(gameDto);
    }

    @GetMapping("/check-availability")
    public boolean checkAvailability(@RequestParam UUID gameId) {
        return gameService.checkAvailability(gameId);
    }

    @DeleteMapping("/remove")
    public void removeGame(@RequestParam UUID gameId) {
        gameService.removeGame(gameId);
    }

//    @GetMapping("find")
//    Page<GameDto> findGames(GameSearchParameters gameSearchParameters, Pageable pageable) {
//        return gameService.findGames(gameSearchParameters, pageable);
//    }
}
