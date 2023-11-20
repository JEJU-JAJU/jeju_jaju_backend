package com.jejujaju.review.model.mapper;

import com.jejujaju.review.model.dto.ReviewDetailResponseDto;
import com.jejujaju.review.model.dto.ReviewDetailSaveDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewDetailMapper {

    @InsertProvider(type = ReviewDetailProvider.class, method = "insertReviewDetail")
    @Options(useGeneratedKeys = true, keyProperty = "reviewDetailId")
    void insertReviewDetail(ReviewDetailSaveDto reviewDetail);

    @Results(id = "reviewDetailMap", value = {
            @Result(property = "reviewDetailId", column = "review_detail_id"),
            @Result(property = "nth", column = "nth"),
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "tagList", column = "review_detail_id",
                    many = @Many(select = "com.jejujaju.review.model.mapper.ReviewDetailTagMapper.selectReviewDetailTagByReviewDetailId"))
    })
    @SelectProvider(type = ReviewDetailProvider.class, method = "selectReviewDetailByReviewId")
    List<ReviewDetailResponseDto> selectReviewDetailByReviewId(Long reviewId);

    @DeleteProvider(type = ReviewDetailProvider.class, method = "deleteReviewDetailByReviewId")
    void deleteReviewDetailByReviewId(Long reviewId);
}
