package pl.aeh.microservices.rentalservice.app.Game;

import java.util.UUID;

public record GameDto(
        UUID id,
        String name,
        Integer quantity
) {
}
