package pl.aeh.microservices.rentalservice.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void gameOrdered(UUID gameId) {
        sendMessage("game-ordered", gameId);
    }

    @Override
    public void gameReturned(UUID gameId) {
        sendMessage("game-returned", gameId);
    }

    private void sendMessage(String topic, Object message) {
        kafkaTemplate.send(topic, message)
                .thenAccept(result -> {
                    log.info("Kafka Producer: Wysłano wiadomość: {} na temat: {}", message, topic);
                    log.info("Kafka Producer: Offset wiadomości: {}", result.getRecordMetadata().offset());
                })
                .exceptionally(e -> {
                    log.error("Kafka Producer: Błąd przy wysyłaniu wiadomości: {}", message, e);
                    return null;
                });
    }
}
