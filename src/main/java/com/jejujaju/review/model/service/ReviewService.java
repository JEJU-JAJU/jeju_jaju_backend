package com.jejujaju.review.model.service;

import com.jejujaju.review.model.dto.ReviewDetailRequestDto;
import com.jejujaju.review.model.dto.ReviewResponseDto;
import com.jejujaju.review.model.dto.ReviewSaveDto;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewSaveDto review, List<ReviewDetailRequestDto> reviewDetail);
    ReviewResponseDto findReviewByReviewId(Long reviewId);
    List<ReviewResponseDto> findReviewByPlanId(Long planId);
    List<ReviewResponseDto> findReviewByUserId(Long userId);
}
