package pl.aeh.microservices.rentalservice.app.RentalOrder;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;



import java.time.LocalDateTime;
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
