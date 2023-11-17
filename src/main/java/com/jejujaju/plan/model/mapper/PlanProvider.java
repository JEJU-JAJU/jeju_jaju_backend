package com.jejujaju.plan.model.mapper;

import org.apache.ibatis.jdbc.SQL;

public class PlanProvider {

    public String insertPlan(){
        return new SQL(){
            {
                INSERT_INTO("plan");
                INTO_COLUMNS("title", "user_id", "description", "hit");
                INTO_VALUES("#{title}", "#{userId}", "#{description}", "0");
            }
        }.toString();
    }

    public String selectPlanByPlanId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("plan");
                WHERE("plan_id = #{planId}");
            }
        }.toString();
    }
}
