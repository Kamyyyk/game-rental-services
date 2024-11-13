package pl.aeh.microservices.inventoryservice.app.stock;

class GameStock {
    private Integer quantity;
    private GameStockEntity entity;

    GameStock(GameStockEntity entity) {
        this.quantity = entity.getChanges().stream()
                .mapToInt(StockChange::getQuantity)
                .sum();
    }

    void registerLoss(Integer quantityLost) {
        if (quantityLost < 1) {
            throw new IllegalArgumentException("Nie można zarejestrować straty mniejszej niż 1");
        }
        int potentialQuantity = quantity - quantityLost;
        if (potentialQuantity < 0) {
            throw new IllegalArgumentException("Stan gry nie może być mniejszy niż zero");
        }
        this.quantity = potentialQuantity;
        entity.addStockChange(-quantityLost);
    }

    void receiveDelivery(Integer quantityReceived) {
        if (quantityReceived < 1) {
            throw new IllegalArgumentException("Nie można zarejestrować dostawy mniejszej niż 1");
        }
        this.quantity += quantityReceived;
        entity.addStockChange(quantityReceived);
    }

    Integer getQuantity() {
        return quantity;
    }

    GameStockEntity getEntity() {
        return entity;
    }
}
