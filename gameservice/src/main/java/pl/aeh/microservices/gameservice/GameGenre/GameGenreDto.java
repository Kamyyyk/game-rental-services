package pl.aeh.microservices.gameservice.GameGenre;

import java.util.UUID;

public record GameGenreDto(
        UUID id,
        String name
) { }
