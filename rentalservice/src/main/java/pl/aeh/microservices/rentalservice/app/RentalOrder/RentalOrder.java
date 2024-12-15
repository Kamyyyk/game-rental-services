package pl.aeh.microservices.rentalservice.app.RentalOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RentalOrder {

    private Date endDate; // Data końca wypożyczenia
    private RentalOrderEntity entity;

    RentalOrder(RentalOrderEntity entity){
        this.entity = entity;
    }
    RentalOrderEntity getEntity() {return entity;}
    Date getendDate() {return endDate;}
}

