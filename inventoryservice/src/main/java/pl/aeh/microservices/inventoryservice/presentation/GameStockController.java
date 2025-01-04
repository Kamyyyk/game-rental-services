package pl.aeh.microservices.inventoryservice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.aeh.microservices.inventoryservice.app.stock.GameStockDto;
import pl.aeh.microservices.inventoryservice.app.stock.GameStockSearchParameters;
import pl.aeh.microservices.inventoryservice.app.stock.GameStockService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
class GameStockController {

    private final GameStockService gameStockService;

    @GetMapping("/inventory-list")
    Page<GameStockDto> findAllByParameters(GameStockSearchParameters parameters, Pageable pageable) {
        return gameStockService.findAllByParameters(parameters, pageable);
    }

    @GetMapping("/total-game-stock")
    GameStockDto getGameStock(UUID gameId) {
        return gameStockService.getGameStockByGameId(gameId);
    }

    @PutMapping("/receive-game-delivery")
    void receiveGameDelivery(@RequestParam UUID gameId, @RequestParam Integer quantity) {
        gameStockService.receiveGameDelivery(gameId, quantity);
    }

    @PutMapping("/register-game-loss")
    void registerGameLoss(@RequestParam UUID gameId, @RequestParam Integer quantity) {
        gameStockService.registerGameLoss(gameId, quantity);
    }
}
