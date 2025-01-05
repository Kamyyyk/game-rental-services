package pl.aeh.microservices.reviewservice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.aeh.microservices.reviewservice.app.review.ReviewDto;
import pl.aeh.microservices.reviewservice.app.review.ReviewService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/review-list")
    List<ReviewDto> findAllByParameters() {
        return reviewService.findAll();
    }

    @GetMapping("/{reviewId}")
    ReviewDto getReview(@PathVariable UUID reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PostMapping("/create-review")
    void createReview(@RequestParam ReviewDto reviewDto) {
        reviewService.addReview(reviewDto);
    }

    @PutMapping("/update-review")
    void updateReview(@RequestParam ReviewDto reviewDto) {
        reviewService.updateReview(reviewDto);
    }

    @DeleteMapping("/delete-review")
    void deleteReview(@RequestParam UUID reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
