package pl.aeh.microservices.reviewservice.messaging;

import pl.aeh.microservices.reviewservice.app.review.ReviewDto;

public interface KafkaProducerService {
    void reviewChanged(ReviewDto reviewDto);
}
