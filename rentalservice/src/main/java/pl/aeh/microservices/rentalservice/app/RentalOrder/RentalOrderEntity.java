package pl.aeh.microservices.rentalservice.app.RentalOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
class RentalOrderEntity {

    @Id
    private UUID id;
    private Date endDate; // Data końca wypożyczenia

}
