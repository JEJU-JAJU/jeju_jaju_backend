package com.jejujaju.event.model.mapper;

import com.jejujaju.event.model.dto.Event;
import org.apache.ibatis.jdbc.SQL;

public class EventProvider {

    public String insertEvent(){
        return new SQL() {
            {
                INSERT_INTO("event");
                INTO_COLUMNS("user_id", "plan_id", "start_date", "end_date", "description", "badge_img");
                INTO_VALUES("#{userId}", "#{planId}", "#{startDate}", "#{endDate}", "#{description}", "#{badgeImg}");
            }
        }.toString();
    }

    public String selectEvent(){
        return new SQL() {
            {
                SELECT("*");
                FROM("event");
            }
        }.toString();
    }

    public String selectEventByEventId(){
        return new SQL() {
            {
                SELECT("*");
                FROM("event");
                WHERE("event_id = #{eventId}");
            }
        }.toString();
    }

    public String updateEvent(Event event){
        return new SQL() {
            {
                UPDATE("event");
                if(event.getStartDate() != null){
                    SET("start_date = #{startDate}");
                }
                if(event.getEndDate() != null){
                    SET("end_date = #{endDate}");
                }
                if(event.getDescription() != null){
                    SET("description = #{description}");
                }
                if(event.getBadgeImg() != null){
                    SET("badge_img = #{badgeImg}");
                }
                WHERE("event_id = #{eventId}");
            }
        }.toString();
    }

    public String deleteEvent(){
        return new SQL(){
            {
                DELETE_FROM("event");
                WHERE("event_id = #{eventId}");
            }
        }.toString();
    }
}
