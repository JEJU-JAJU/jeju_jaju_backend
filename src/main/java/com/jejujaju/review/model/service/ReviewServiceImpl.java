package com.jejujaju.review.model.service;

import com.jejujaju.review.model.dto.*;
import com.jejujaju.review.model.mapper.ReviewDetailMapper;
import com.jejujaju.review.model.mapper.ReviewDetailTagMapper;
import com.jejujaju.review.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewDetailMapper reviewDetailMapper;
    private final ReviewDetailTagMapper reviewDetailTagMapper;


    @Override
    public void saveReview(ReviewSaveDto review, List<ReviewDetailRequestDto> reviewDetail) {
        reviewMapper.insertReview(review);

        int nth = 0;
        for (ReviewDetailRequestDto reviewDetailDto : reviewDetail) {
            ReviewDetailSaveDto newReviewDetail = ReviewDetailSaveDto.builder()
                    .reviewId(review.getReviewId())
                    .placeId(reviewDetailDto.getPlaceId())
                    .nth((long) ++nth)
                    .build();
            reviewDetailMapper.insertReviewDetail(newReviewDetail);

            for (Long tagId : reviewDetailDto.getTagList()) {
                reviewDetailTagMapper.insertReviewDetailTag(
                        ReviewDetailTagSaveDto.builder()
                                .reviewDetailId(newReviewDetail.getReviewDetailId())
                                .tagId(tagId)
                                .build()
                );
            }
        }
    }

    @Override
    public ReviewResponseDto findReviewByReviewId(Long reviewId) {
        return reviewMapper.selectReviewByReviewId(reviewId);
    }

    @Override
    public List<ReviewResponseDto> findReviewByPlanId(Long planId) {
        return reviewMapper.selectReviewByPlanId(planId);
    }
}
