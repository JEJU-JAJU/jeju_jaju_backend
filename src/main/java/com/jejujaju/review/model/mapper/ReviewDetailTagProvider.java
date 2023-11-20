package com.jejujaju.review.model.mapper;

import org.apache.ibatis.jdbc.SQL;

public class ReviewDetailTagProvider {
    public String insertReviewDetailTag(){
        return new SQL(){
            {
                INSERT_INTO("review_detail_tag");
                INTO_COLUMNS("review_detail_id", "tag_id");
                INTO_VALUES("#{reviewDetailId}", "#{tagId}");
            }
        }.toString();
    }

    public String selectReviewDetailTagByReviewDetailId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("review_detail_tag");
                WHERE("review_detail_id = #{reviewDetailId}");
            }
        }.toString();
    }

    public String deleteReviewDetailTagByReviewDetailId(){
        return new SQL(){
            {
                DELETE_FROM("review_detail_tag");
                WHERE("review_detail_id = #{reviewDetailId}");
            }
        }.toString();
    }
}
