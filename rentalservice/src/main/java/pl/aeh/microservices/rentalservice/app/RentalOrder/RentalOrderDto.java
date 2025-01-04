package pl.aeh.microservices.rentalservice.app.RentalOrder;

import java.time.LocalDateTime;
import java.util.UUID;

public record RentalOrderDto(
        UUID id,
        LocalDateTime orderDateTime,
        UUID gameid,
        LocalDateTime returnDateTime) {
}


