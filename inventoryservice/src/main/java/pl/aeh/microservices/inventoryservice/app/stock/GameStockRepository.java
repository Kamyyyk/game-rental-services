package pl.aeh.microservices.inventoryservice.app.stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface GameStockRepository extends JpaRepository<GameStockEntity, UUID> {

    Page<GameStockEntity> findAll(Specification<GameStockEntity> spec, Pageable pageable);
}
