package pl.aeh.microservices.reviewservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.reviewservice.app.review.ReviewDto;
import pl.aeh.microservices.reviewservice.app.review.ReviewService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @Override
    public void reviewChanged(ReviewDto reviewDto) {
        Integer totalReviews = reviewService.getReviewsByGameId(reviewDto.gameId()).size();
        Double averageRate = reviewService.getAverageRating(reviewDto.gameId());
        ReviewChangedMessage message = new ReviewChangedMessage(
                UUID.randomUUID(),
                reviewDto.gameId(),
                totalReviews,
                averageRate
        );
        sendMessage("review-changed", message);
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
