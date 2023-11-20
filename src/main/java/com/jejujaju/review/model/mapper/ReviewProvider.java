package com.jejujaju.review.model.mapper;

import org.apache.ibatis.jdbc.SQL;

public class ReviewProvider {

    public String insertReview(){
        return new SQL(){
            {
                INSERT_INTO("review");
                INTO_COLUMNS("plan_id", "user_id", "description");
                INTO_VALUES("#{planId}", "#{userId}", "#{description}");
            }
        }.toString();
    }

    public String selectReviewByReviewId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("review");
                WHERE("review_id = #{reviewId}");
            }
        }.toString();
    }

    public String selectReviewByPlanId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("review");
                WHERE("plan_id = #{planId}");
            }
        }.toString();
    }
}
