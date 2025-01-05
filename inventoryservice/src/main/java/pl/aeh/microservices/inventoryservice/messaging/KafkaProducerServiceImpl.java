package pl.aeh.microservices.inventoryservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.inventoryservice.app.game.GameDto;

@Service
@Slf4j
@RequiredArgsConstructor
class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void stockChanged(GameDto game, Integer quantity) {
        sendMessage("game-stock-changed", new GameStockChangedMessage(game, quantity));
    }

    private void sendMessage(String topic, Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, jsonMessage)
                    .thenAccept(result -> {
                        log.info("Kafka Producer: Wysłano wiadomość: {} na temat: {}", message, topic);
                        log.info("Kafka Producer: Offset wiadomości: {}", result.getRecordMetadata().offset());
                    })
                    .exceptionally(e -> {
                        log.error("Kafka Producer: Błąd przy wysyłaniu wiadomości: {}", message, e);
                        return null;
                    });
        } catch (JsonProcessingException e) {
            log.error("Can't map object to JSON: {}", message, e);
        }
    }
}
