package pl.aeh.microservices.reviewservice.app.game;

import java.util.UUID;

public record GameDto(
        UUID id,
        String name
) {
}
