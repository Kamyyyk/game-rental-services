package pl.aeh.microservices.reviewservice.messaging;

public interface KafkaProducerService {
    void reviewChanged(ReviewChangedMessage reviewDto);
}
