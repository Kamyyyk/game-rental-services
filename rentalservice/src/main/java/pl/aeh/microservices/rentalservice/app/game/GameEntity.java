package pl.aeh.microservices.rentalservice.app.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
class GameEntity {
    @Id
    private UUID id;
    private String name;
    private Integer quantity;

    public GameEntity() {
    }

    public GameEntity(UUID id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public GameDto toDto() {
        return new GameDto(id, name, quantity);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
