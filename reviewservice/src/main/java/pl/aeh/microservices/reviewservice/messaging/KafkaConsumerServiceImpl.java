package pl.aeh.microservices.reviewservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.reviewservice.app.game.GameService;
import pl.aeh.microservices.reviewservice.app.review.ReviewService;

import javax.security.auth.login.CredentialException;

@Slf4j
@Service
@RequiredArgsConstructor
class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @Override
    public void listenGameCreatedMessage(String message) {
        reviewService.addGame(readValue(message, GameCreatedMessage.class));
    }

    @Override
    public void listenGameRemovedMessage(String message) {
        reviewService.removeGame(readValue(message, GameRemovedMessage.class));
    }

    @Override
    public void listenGameUpdatedMessage(String message) {
        reviewService.renameGame(readValue(message, GameUpdatedMessage.class));
    }

    public <T> T readValue(String message, Class<T> clazz) {
        try {
            return objectMapper.readValue(message, clazz);
        } catch (Exception e) {
            log.error("Cannot read value of class {} from message: {}", clazz.getName(), message, e);
            return null;
        }
    }
}