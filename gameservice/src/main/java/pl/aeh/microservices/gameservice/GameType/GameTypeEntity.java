package pl.aeh.microservices.gameservice.GameType;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "game_type")
public class GameTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name")
    private String name;
}
