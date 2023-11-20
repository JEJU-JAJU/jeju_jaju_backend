package com.jejujaju.event.model.service;

import com.jejujaju.event.model.dto.Event;

import java.util.List;

public interface EventService {
    void saveEvent(Event event);
    List<Event> findAllEvent();
    Event findEventByEventId(Long eventId);
}
