package pl.aeh.microservices.rentalservice.app.rentalOrder;

import java.util.UUID;

public interface RentalOrderService {


    void createRentalOrder(UUID gameid);

    void gameReturn(UUID gameid);


}
