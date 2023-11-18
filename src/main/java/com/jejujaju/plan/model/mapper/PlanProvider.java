package com.jejujaju.plan.model.mapper;

import com.jejujaju.plan.model.dto.PlanSaveDto;
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

    public String selectAllPlan(){
        return new SQL(){
            {
                SELECT("*");
                FROM("plan");
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

    public String updatePlan(PlanSaveDto plan){
        return new SQL(){
            {
                UPDATE("plan");
                if(plan.getTitle() != null){
                    SET("title = #{title}");
                }
                if(plan.getDescription() != null){
                    SET("description = #{description}");
                }
                WHERE("plan_id = #{planId}");
            }
        }.toString();
    }

    public String deletePlan(){
        return new SQL(){
            {
                DELETE_FROM("plan");
                WHERE("plan_id = #{plan_id}");
            }
        }.toString();
    }

    public String updateHit(){
        return new SQL(){
            {
                UPDATE("plan");
                SET("hit = hit + 1");
                WHERE("plan_id = #{plan_id}");
            }
        }.toString();
    }
}
