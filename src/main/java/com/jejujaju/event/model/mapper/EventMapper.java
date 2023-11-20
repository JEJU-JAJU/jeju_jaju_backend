package com.jejujaju.event.model.mapper;

import com.jejujaju.event.model.dto.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {

    @Insert("INSERT INTO event (user_id, plan_id, start_date, end_date, description, badge_img)" +
            "VALUES (#{userId}, #{planId}, #{startDate}, #{endDate}, #{description}, #{badgeImg})")
    void insertEvent(Event event);

    @Results(id = "eventMap", value = {
        @Result(property = "eventId", column = "event_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "planId", column = "plan_id"),
        @Result(property = "startDate", column = "start_date"),
        @Result(property = "endDate", column = "end_date"),
        @Result(property = "description", column = "description"),
        @Result(property = "badgeImg", column = "badge_img"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at"),
    })
    @Select("SELECT * FROM event")
    List<Event> selectEvent();

    @ResultMap("eventMap")
    @Select("SELECT * FROM event WHERE event_id = #{eventId}")
    Event selectEventByEventId(Long eventId);
}
