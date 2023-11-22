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
        saveReviewDetail(review.getReviewId(), reviewDetail);
    }

    @Override
    public ReviewResponseDto findReviewByReviewId(Long reviewId) {
        return reviewMapper.selectReviewByReviewId(reviewId);
    }

    @Override
    public List<ReviewResponseDto> findReviewByPlanId(Long planId) {
        return reviewMapper.selectReviewByPlanId(planId);
    }

    @Override
    public List<ReviewResponseDto> findReviewByUserId(Long userId) {
        return reviewMapper.selectReviewByUserId(userId);
    }

    @Override
    public void modifyReview(ReviewSaveDto review, List<ReviewDetailRequestDto> reviewDetail) {
        reviewMapper.updateReviewDescription(review);
        deleteReviewDetail(review.getReviewId());
        saveReviewDetail(review.getReviewId(), reviewDetail);
    }

    @Override
    public void deleteReview(Long reviewId) {
        deleteReviewDetail(reviewId);
        reviewMapper.deleteReview(reviewId);
    }

    public void saveReviewDetail(Long reviewId, List<ReviewDetailRequestDto> reviewDetail){
        int nth = 0;
        for (ReviewDetailRequestDto reviewDetailDto : reviewDetail) {
            ReviewDetailSaveDto newReviewDetail = ReviewDetailSaveDto.builder()
                    .reviewId(reviewId)
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

    public void deleteReviewDetail(Long reviewId){
        ReviewResponseDto reviewResponseDto = reviewMapper.selectReviewByReviewId(reviewId);
        for(ReviewDetailResponseDto reviewDetail : reviewResponseDto.getReviewDetailList()){
            reviewDetailTagMapper.deleteReviewDetailTagByReviewDetailId(reviewDetail.getReviewDetailId());
        }
        reviewDetailMapper.deleteReviewDetailByReviewId(reviewId);
    }
}
