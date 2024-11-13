package pl.aeh.microservices.inventoryservice.app.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
class StockChange {
    @Id
    private UUID id;
    private LocalDateTime timestamp;
    private Integer quantity;

    public StockChange(Integer quantity) {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.quantity = quantity;
    }

    public StockChange() {

    }
}
