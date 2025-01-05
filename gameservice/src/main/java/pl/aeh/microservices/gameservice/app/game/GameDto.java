package pl.aeh.microservices.gameservice.app.game;

import pl.aeh.microservices.gameservice.GameGenre.GameGenreEntity;
import pl.aeh.microservices.gameservice.GameType.GameTypeEntity;

import java.util.UUID;

public record GameDto(
        UUID id,
        String title,
        String description,
//        GameTypeEntity type_id,
//        GameGenreEntity genre_id,
        int players_from,
        int players_to,
        int age_from,
        int age_to,
        boolean available,
        int totalReviews,
        int averageRate
) {}
