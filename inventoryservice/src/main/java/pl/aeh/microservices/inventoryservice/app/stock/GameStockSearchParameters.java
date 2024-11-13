package pl.aeh.microservices.inventoryservice.app.stock;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameStockSearchParameters {
    private UUID gameId;
    private LocalDateTime snapshotTimestamp;

    Specification<GameStockEntity> toSpecification() {
        return Specification
                .where(gameIdEquals())
                .and(timestampBefore());
    }

    private Specification<GameStockEntity> gameIdEquals() {
        if (gameId != null) {
            return (root, query, cb) -> cb.equal(root.get("id"), gameId);
        }
        return null;
    }

    private Specification<GameStockEntity> timestampBefore() {
        if (snapshotTimestamp != null) {
            return (root, query, cb) -> {
                Join<GameStockEntity, StockChange> changesJoin = root.join("changes", JoinType.LEFT);
                if (query != null) {
                    query.distinct(true);
                }
                return cb.lessThanOrEqualTo(changesJoin.get("timestamp"), snapshotTimestamp);
            };
        }
        return null;
    }
}
