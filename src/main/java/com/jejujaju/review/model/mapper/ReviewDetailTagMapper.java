package com.jejujaju.review.model.mapper;

import com.jejujaju.review.model.dto.ReviewDetailTagResponseDto;
import com.jejujaju.review.model.dto.ReviewDetailTagSaveDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReviewDetailTagMapper {

    @SelectProvider(type = ReviewDetailTagProvider.class, method = "insertReviewDetailTag")
    void insertReviewDetailTag(ReviewDetailTagSaveDto reviewDetailTag);

    @Results(id = "reviewDetailTagMap", value = {
            @Result(property = "reviewDetailTagId", column = "review_detail_tag_id"),
            @Result(property = "reviewDetailId", column = "review_detail_id"),
            @Result(property = "tag", column = "tag_id",
                    one = @One(select = "com.jejujaju.tag.model.mapper.TagMapper.selectTagByTagId"))
    })
    @SelectProvider(type = ReviewDetailTagProvider.class, method = "selectReviewDetailTagByReviewDetailId")
    ReviewDetailTagResponseDto selectReviewDetailTagByReviewDetailId(Long reviewDetailId);
}
