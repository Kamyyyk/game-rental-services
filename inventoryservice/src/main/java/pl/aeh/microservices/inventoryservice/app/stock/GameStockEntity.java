package pl.aeh.microservices.inventoryservice.app.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
class GameStockEntity {
    @Id
    private UUID id;
    @OneToMany
    private Set<StockChange> changes;

    void addStockChange(Integer quantity) {
        this.changes.add(new StockChange(quantity));
    }
}

