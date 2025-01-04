package pl.aeh.microservices.rentalservice.app.RentalOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
class RentalOrderEntity {

    @Id
    private UUID id;
    private LocalDateTime orderDateTime;
    private UUID gameid;
    private LocalDateTime returnDateTime;

    public RentalOrderDto toDto() {
        return new RentalOrderDto(id, orderDateTime, gameid, returnDateTime);
    }

    public void returnOrder() {
        returnDateTime = LocalDateTime.now();
    }


}
