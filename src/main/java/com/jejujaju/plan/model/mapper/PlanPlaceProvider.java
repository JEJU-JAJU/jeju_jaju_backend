package com.jejujaju.plan.model.mapper;

import org.apache.ibatis.jdbc.SQL;

public class PlanPlaceProvider {

    public String insertPlanPlace(){
        return new SQL(){
            {
                INSERT_INTO("plan_place");
                INTO_COLUMNS("plan_id", "nth", "place_id");
                INTO_VALUES("#{planId}", "#{nth}", "#{placeId}");
            }
        }.toString();
    }

    public String selectPlanPlaceByPlanId(){
        return new SQL(){
            {
                SELECT("*");
                FROM("plan_place");
                WHERE("plan_id = #{planId}");
            }
        }.toString();
    }

    public String deletePlanPlaceByPlanPlaceId(){
        return new SQL(){
            {
                DELETE_FROM("plan_place");
                WHERE("plan_place_id = #{planPlaceId}");
            }
        }.toString();
    }

    public String deletePlanPlaceByPlanId(){
        return new SQL(){
            {
                DELETE_FROM("plan_place");
                WHERE("plan_id = #{planId}");
            }
        }.toString();
    }
}
