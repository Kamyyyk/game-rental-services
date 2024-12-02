package pl.aeh.microservices.rentalservice.app;


import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.UUID;

public class RentalOrderSearchParameters {
    private UUID orderId;
    private Date enddate;

    Specification<RentalOrderEntity> toSpecification() {
        return Specification
                .where(orderIdEquals());
    }

    private Specification<RentalOrderEntity> orderIdEquals() {
        if (orderId != null) {
            return (root, query, cb) -> cb.equal(root.get("id"), orderId);
        }
        return null;
    }
}
