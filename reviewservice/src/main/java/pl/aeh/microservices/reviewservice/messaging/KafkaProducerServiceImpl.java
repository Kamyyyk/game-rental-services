package pl.aeh.microservices.reviewservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.aeh.microservices.reviewservice.app.review.ReviewService;

@Slf4j
@Service
@RequiredArgsConstructor
class KafkaProducerServiceImpl implements KafkaProducerService {

    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

}
