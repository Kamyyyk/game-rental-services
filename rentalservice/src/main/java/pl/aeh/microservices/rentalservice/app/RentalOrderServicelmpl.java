package pl.aeh.microservices.rentalservice.app;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import pl.aeh.microservices.rentalservice.app.RentalOrderDto;
import pl.aeh.microservices.rentalservice.app.RentalOrderService;

import java.util.Date;
import java.util.UUID;




@Slf4j
@Service
@RequiredArgsConstructor
class  RentalOrderServiceImpl implements RentalOrderService {

    private final RentalOrderRepository rentalOrderRepository;
    private final RentalOrderService rentalOrderService;


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
    public void editRentalOrder(UUID orderID) {

    }

    @Override
    public void removeRentalOrder(UUID orderID) {

    }
}
