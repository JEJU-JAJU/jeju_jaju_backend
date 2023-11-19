package com.jejujaju.review.controller;

import com.jejujaju.review.model.dto.ReviewRequestDto;
import com.jejujaju.review.model.dto.ReviewSaveDto;
import com.jejujaju.review.model.service.ReviewService;
import com.jejujaju.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans/{plan-id}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> saveReview(@AuthenticationPrincipal User user, @PathVariable("plan-id") Long planId,
                                           @RequestBody ReviewRequestDto review) {
        ReviewSaveDto newReview = ReviewSaveDto.builder()
                .planId(planId)
                .userId(user.getUserId())
                .description(review.getDescription())
                .build();

        reviewService.saveReview(newReview, review.getReviewDetailList());
        return ResponseEntity.ok().build();
    }
}
