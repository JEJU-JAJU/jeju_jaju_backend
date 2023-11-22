package com.jejujaju.event.model.service;

import com.jejujaju.event.model.dto.Event;
import com.jejujaju.event.model.dto.EventBadgeDto;
import com.jejujaju.event.model.dto.EventImg;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    void saveEvent(Event event, List<MultipartFile> files) throws IOException;
    List<Event> findAllEvent();
    Event findEventByEventId(Long eventId);
    List<EventImg> findEventImgByEventId(Long eventId) throws IOException;
    void modifyEvent(Event event, List<MultipartFile> files) throws IOException;
    void deleteEvent(Long eventId);
    List<EventBadgeDto> findBadgesByUserId(Long userId);
}
