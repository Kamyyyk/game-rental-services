package pl.aeh.microservices.gameservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.gameservice.app.game.GameDto;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void gameCreated(GameDto game) {
        sendMessage("game-created", new GameCreatedMessage(UUID.randomUUID(), game.id(), game.title()));
    }

    @Override
    public void gameUpdated(GameDto game) {
        sendMessage("game-updated", new GameUpdatedMessage(game));
    }

    @Override
    public void gameRemoved(UUID gameId) {
        sendMessage("game-removed", new GameRemovedMessage(gameId));
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
