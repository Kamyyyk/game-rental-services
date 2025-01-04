package pl.aeh.microservices.inventoryservice.app.stock;

import pl.aeh.microservices.inventoryservice.app.game.GameDto;

import java.util.UUID;

public record GameStockDto(
        UUID id,
        String game,
        Integer quantity) {
    public GameStockDto(GameDto game, Integer quantity) {
        this(UUID.randomUUID(), game.name(), quantity);
    }
}
