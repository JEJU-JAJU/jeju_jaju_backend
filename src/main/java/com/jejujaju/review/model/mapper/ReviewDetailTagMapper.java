package com.jejujaju.review.model.mapper;

import com.jejujaju.review.model.dto.ReviewDetailTagSaveDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface ReviewDetailTagMapper {

    @SelectProvider(type = ReviewDetailTagProvider.class, method = "insertReviewDetailTag")
    void insertReviewDetailTag(ReviewDetailTagSaveDto reviewDetailTag);
}
