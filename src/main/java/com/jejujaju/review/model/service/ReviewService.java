package com.jejujaju.review.model.service;

import com.jejujaju.review.model.dto.ReviewDetailRequestDto;
import com.jejujaju.review.model.dto.ReviewSaveDto;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewSaveDto review, List<ReviewDetailRequestDto> reviewDetail);
}
