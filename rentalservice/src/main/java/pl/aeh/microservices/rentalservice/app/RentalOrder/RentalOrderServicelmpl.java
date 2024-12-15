package pl.aeh.microservices.rentalservice.app.RentalOrder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.rentalservice.app.Game.GameDto;
import pl.aeh.microservices.rentalservice.app.Game.GameService;

import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
class  RentalOrderServiceImpl implements RentalOrderService {

    private final RentalOrderRepository rentalOrderRepository;
    private final GameService gameService;




    @Override
    public boolean checkAvailability(UUID game_id) {
        return false;
    }

    @Override
    public Page<RentalOrder> userRentalHistory(RentalOrderSearchParameters parameters,Pageable pageable) {
       return null;
    }


    @Override
    public void createRentalOrder(UUID orderID, UUID game_id, Date end_date) {

    }

    @Override
    public void editRentalOrder(UUID orderID, UUID game_id, Date end_date) {

    }

    @Override
    public void removeRentalOrder(UUID orderID) {

    }
}
