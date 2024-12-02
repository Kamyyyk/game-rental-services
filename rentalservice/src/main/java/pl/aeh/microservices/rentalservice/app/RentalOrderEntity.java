package pl.aeh.microservices.rentalservice.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
public class RentalOrderEntity {

    @Id
    private UUID id;
    private UUID game_id; // ID wypożyczanej gry
    private Date endDate; // Data końca wypożyczenia

}
