package com.jejujaju.review.model.mapper;

import com.jejujaju.review.model.dto.ReviewResponseDto;
import com.jejujaju.review.model.dto.ReviewSaveDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @InsertProvider(type = ReviewProvider.class, method = "insertReview")
    @Options(useGeneratedKeys = true, keyProperty = "reviewId")
    void insertReview(ReviewSaveDto review);

    @Results(id = "reviewMap", value = {
            @Result(property = "reviewId", column = "review_id"),
            @Result(property = "planId", column = "plan_id"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.jejujaju.user.model.mapper.UserMapper.selectUserByUserId")),
            @Result(property = "description", column = "description"),
            @Result(property = "reviewDetailList", column = "review_id",
                    many = @Many(select = "com.jejujaju.review.model.mapper.ReviewDetailMapper.selectReviewDetailByReviewId")),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @SelectProvider(type = ReviewProvider.class, method = "selectReviewByReviewId")
    ReviewResponseDto selectReviewByReviewId(Long reviewId);

    @ResultMap("reviewMap")
    @SelectProvider(type = ReviewProvider.class, method = "selectReviewByPlanId")
    List<ReviewResponseDto> selectReviewByPlanId(Long planId);

    @ResultMap("reviewMap")
    @SelectProvider(type = ReviewProvider.class, method = "selectReviewByUserId")
    List<ReviewResponseDto> selectReviewByUserId(Long userId);

    @UpdateProvider(type = ReviewProvider.class, method = "updateReviewDescription")
    void updateReviewDescription(ReviewSaveDto review);

    @DeleteProvider(type = ReviewProvider.class, method = "deleteReview")
    void deleteReview(Long reviewId);
}
