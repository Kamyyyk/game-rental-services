package pl.aeh.microservices.rentalservice.app.Game;

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

    public GameEntity() { }

    public GameEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public GameDto toDto() {
        return new GameDto(id, name);
    }

    public void changeName(String name) {
        this.name = name;
    }
}
