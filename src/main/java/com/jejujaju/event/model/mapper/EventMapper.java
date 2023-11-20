package com.jejujaju.event.model.mapper;

import com.jejujaju.event.model.dto.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {

    @InsertProvider(type = EventProvider.class, method = "insertEvent")
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
    @SelectProvider(type = EventProvider.class, method = "selectEvent")
    List<Event> selectEvent();

    @ResultMap("eventMap")
    @SelectProvider(type = EventProvider.class, method = "selectEventByEventId")
    Event selectEventByEventId(Long eventId);

    @UpdateProvider(type = EventProvider.class, method = "updateEvent")
    void updateEvent(Event event);

    @DeleteProvider(type = EventProvider.class, method = "deleteEvent")
    void deleteEvent(Long eventId);
}
