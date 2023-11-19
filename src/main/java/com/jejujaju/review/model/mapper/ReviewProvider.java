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
}
