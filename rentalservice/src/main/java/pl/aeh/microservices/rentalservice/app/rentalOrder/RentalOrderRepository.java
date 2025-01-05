package pl.aeh.microservices.rentalservice.app.rentalOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface RentalOrderRepository extends JpaRepository<RentalOrderEntity, UUID> {

    Page<RentalOrderEntity> findAll(Specification<RentalOrderEntity> spec, Pageable pageable);
}
