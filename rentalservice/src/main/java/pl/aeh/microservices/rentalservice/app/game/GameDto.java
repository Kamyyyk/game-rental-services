package pl.aeh.microservices.rentalservice.app.game;

import java.util.UUID;

public record GameDto(
        UUID id,
        String name,
        Integer quantity
) {
}
