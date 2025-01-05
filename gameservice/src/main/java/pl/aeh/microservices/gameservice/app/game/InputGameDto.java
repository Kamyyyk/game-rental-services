package pl.aeh.microservices.gameservice.app.game;

import java.util.UUID;

public record InputGameDto(
        UUID id,
        String title,
        String description,
        int playersFrom,
        int playersTo,
        int ageFrom,
        int ageTo
) {
}
