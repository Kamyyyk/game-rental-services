package pl.aeh.microservices.rentalservice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.aeh.microservices.rentalservice.app.rentalOrder.RentalOrderDto;
import pl.aeh.microservices.rentalservice.app.rentalOrder.RentalOrderSearchParameters;
import pl.aeh.microservices.rentalservice.app.rentalOrder.RentalOrderService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
class GameOrderController {

    private final RentalOrderService rentalOrderService;

    @PutMapping("/order-game")
    void orderGame(@RequestParam UUID gameID) {
        rentalOrderService.createRentalOrder(gameID);

    }

    @PutMapping("/return-game")
    void returnGame() {
    }

    @GetMapping("")
    Page<RentalOrderDto> findRentalOrders(RentalOrderSearchParameters searchParameters) {
        return null;
    }

}
