package pl.aeh.microservices.rentalservice.app.rentalOrder;

import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;


public class RentalOrderSearchParameters {
    private UUID gameId;


    Specification<RentalOrderEntity> toSpecification() {
        return Specification
                .where(gameIdEquals());

    }

    private Specification<RentalOrderEntity> gameIdEquals() {
        if (gameId != null) {
            return (root, query, cb) -> cb.equal(root.get("id"), gameId);
        }
        return null;
    }


}
