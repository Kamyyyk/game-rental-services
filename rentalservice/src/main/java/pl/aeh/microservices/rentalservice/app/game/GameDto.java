package pl.aeh.microservices.inventoryservice.app.game;

import java.util.UUID;

public record GameDto(
        UUID id,
        String name
) {
}
