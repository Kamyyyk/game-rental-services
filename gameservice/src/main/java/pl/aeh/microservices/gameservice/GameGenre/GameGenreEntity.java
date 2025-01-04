package pl.aeh.microservices.gameservice.GameGenre;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class GameGenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @Column(name = "name")
    String name;
}
