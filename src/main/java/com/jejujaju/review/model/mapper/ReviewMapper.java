package com.jejujaju.review.model.mapper;

import com.jejujaju.review.model.dto.ReviewSaveDto;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ReviewMapper {

    @InsertProvider(type = ReviewProvider.class, method = "insertReview")
    @Options(useGeneratedKeys = true, keyProperty = "reviewId")
    void insertReview(ReviewSaveDto review);
}
