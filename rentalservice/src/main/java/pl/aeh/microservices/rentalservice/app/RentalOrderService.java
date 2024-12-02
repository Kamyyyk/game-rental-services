package pl.aeh.microservices.rentalservice.app;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.UUID;

public interface RentalOrderService {

    boolean checkAvailability(UUID orderId);

    Page<RentalOrder> userRentalHistory(Pageable pageable);

    void createRentalOrder(UUID orderID, UUID game_id, Date end_date);

    void editRentalOrder(UUID orderID);

    void removeRentalOrder(UUID orderID);

}
