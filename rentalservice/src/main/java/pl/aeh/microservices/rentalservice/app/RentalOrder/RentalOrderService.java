package pl.aeh.microservices.rentalservice.app.RentalOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.UUID;

public interface RentalOrderService {

    boolean checkAvailability(UUID game_id);

    Page<RentalOrder> userRentalHistory(RentalOrderSearchParameters parameters,Pageable pageable);

    void createRentalOrder(UUID orderID, UUID game_id, Date end_date);

    void editRentalOrder(UUID orderID, UUID game_id, Date end_date);

    void removeRentalOrder(UUID orderID);

}
