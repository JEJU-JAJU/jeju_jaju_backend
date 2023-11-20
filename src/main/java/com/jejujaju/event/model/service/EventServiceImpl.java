package com.jejujaju.event.model.service;

import com.jejujaju.event.model.dto.Event;
import com.jejujaju.event.model.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService{

    private final EventMapper eventMapper;

    @Override
    public void saveEvent(Event event) {
        eventMapper.insertEvent(event);
    }

    @Override
    public List<Event> findAllEvent() {
        return eventMapper.selectEvent();
    }

    @Override
    public Event findEventByEventId(Long eventId) {
        return eventMapper.selectEventByEventId(eventId);
    }

    @Override
    public void modifyEvent(Event event) {
        eventMapper.updateEvent(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventMapper.deleteEvent(eventId);
    }
}
