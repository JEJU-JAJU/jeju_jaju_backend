package com.jejujaju.review.model.mapper;

import org.apache.ibatis.jdbc.SQL;

public class ReviewDetailProvider {
    public String insertReviewDetail(){
        return new SQL(){
            {
                INSERT_INTO("review_detail");
                INTO_COLUMNS("review_id", "nth", "place_id");
                INTO_VALUES("#{reviewId}", "#{nth}", "#{placeId}");
            }
        }.toString();
    }
}
