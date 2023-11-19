package com.jejujaju.review.model.mapper;

import com.jejujaju.review.model.dto.ReviewDetailSaveDto;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ReviewDetailMapper {

    @InsertProvider(type = ReviewDetailProvider.class, method = "insertReviewDetail")
    @Options(useGeneratedKeys = true, keyProperty = "reviewDetailId")
    void insertReviewDetail(ReviewDetailSaveDto reviewDetail);
}
