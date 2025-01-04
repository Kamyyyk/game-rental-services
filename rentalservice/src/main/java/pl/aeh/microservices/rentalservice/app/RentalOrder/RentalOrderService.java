package pl.aeh.microservices.rentalservice.app.RentalOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.UUID;

public interface RentalOrderService {



    void createRentalOrder(UUID gameid);
    void gameReturn(UUID gameid);



}
