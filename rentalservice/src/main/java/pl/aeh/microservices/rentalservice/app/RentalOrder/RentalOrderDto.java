package pl.aeh.microservices.rentalservice.app.RentalOrder;

import pl.aeh.microservices.rentalservice.app.Game.GameDto;

import java.util.Date;
import java.util.UUID;

public record RentalOrderDto (
    UUID id,
    String game,
    Date endDate){
    public RentalOrderDto(GameDto game, Date endDate){
        this(UUID.randomUUID(), game.name(), endDate);
    }
        }


