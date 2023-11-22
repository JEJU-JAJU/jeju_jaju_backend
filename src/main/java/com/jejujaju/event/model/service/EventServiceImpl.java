package com.jejujaju.event.model.service;

import com.jejujaju.event.model.dto.*;
import com.jejujaju.event.model.mapper.EventImgMapper;
import com.jejujaju.event.model.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService{

    private final EventMapper eventMapper;
    private final EventImgMapper eventImgMapper;
    private final FileHandler fileHandler;

    @Override
    public void saveEvent(Event event, List<MultipartFile> files) throws IOException {
        eventMapper.insertEvent(event);
        List<EventImg> list = fileHandler.parseFileInfo(event.getEventId(), files);

        for(EventImg img : list){
            img.setEventId(event.getEventId());
            eventImgMapper.insertEventImg(img);
        }
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
    public List<EventImg> findEventImgByEventId(Long eventId) throws IOException {
        return eventImgMapper.selectEventByEventId(eventId);
    }

    @Override
    public void modifyEvent(Event event) {
        eventMapper.updateEvent(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventMapper.deleteEvent(eventId);
    }

    @Override
    public List<EventBadgeDto> findBadgesByUserId(Long userId) {
        return eventMapper.selectBadgesByUserId(userId);
    }
}
