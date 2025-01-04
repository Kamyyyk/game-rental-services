package pl.aeh.microservices.rentalservice.app.RentalOrder;

import pl.aeh.microservices.rentalservice.app.Game.GameDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record RentalOrderDto (
        UUID id,
        LocalDateTime orderDateTime,
        UUID gameid,
        LocalDateTime returnDateTime) { }


