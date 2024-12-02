package pl.aeh.microservices.rentalservice.app;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RentalOrder {

    private UUID id;
    private UUID game_id; // ID wypożyczanej gry
    private Date endDate; // Data końca wypożyczenia

    public RentalOrder() {}
    // Konstruktor
    public RentalOrder(UUID id, UUID game, Date endDate) {
        this.id = id;
        this.game_id = game;
        this.endDate = endDate;
    }


}

