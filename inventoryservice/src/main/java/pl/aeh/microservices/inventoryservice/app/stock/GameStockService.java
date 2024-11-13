package pl.aeh.microservices.inventoryservice.app.stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GameStockService {

    Page<GameStockDto> findAllByParameters(GameStockSearchParameters parameters, Pageable pageable);

    GameStockDto getGameStockByGameId(UUID gameId);

    void receiveGameDelivery(UUID gameId, Integer quantity);

    void registerGameLoss(UUID gameId, Integer quantity);
}
