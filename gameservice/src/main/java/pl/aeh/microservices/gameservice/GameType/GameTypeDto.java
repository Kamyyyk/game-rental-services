package pl.aeh.microservices.gameservice.GameType;

import java.util.UUID;

public record GameTypeDto(
        UUID id,
        String name
) {}
