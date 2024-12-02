package pl.aeh.microservices.rentalservice.app;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aeh.microservices.rentalservice.app.RentalOrderDto;
import pl.aeh.microservices.rentalservice.app.RentalOrderService;

import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
@RequiredArgsConstructor
public class RentalOrderServicelmpl {

    private final RentalOrderRepository rentalOrderRepository;
    private final RentalOrderService rentalOrderService;




}
