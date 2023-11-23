package com.jejujaju.event.model.service;

import com.jejujaju.event.model.dto.Event;
import com.jejujaju.event.model.dto.EventBadgeDto;
import com.jejujaju.event.model.dto.EventImg;
import com.jejujaju.util.FileHandler;
import com.jejujaju.event.model.mapper.EventImgMapper;
import com.jejujaju.event.model.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        saveImg(event.getEventId(), files);
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
    public void modifyEvent(Event event, List<MultipartFile> files) throws IOException {
        eventMapper.updateEvent(event);
        if(files != null) {
            eventImgMapper.deleteEventImgByEventId(event.getEventId());
            saveImg(event.getEventId(), files);
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventMapper.deleteEvent(eventId);
    }

    @Override
    public List<EventBadgeDto> findBadgesByUserId(Long userId) {
        return eventMapper.selectBadgesByUserId(userId);
    }

    public void saveImg(Long eventId, List<MultipartFile> files) throws IOException{
        List<EventImg> list = fileHandler.parseFileInfo(eventId, files);

        for(EventImg img : list){
            img.setEventId(eventId);
            eventImgMapper.insertEventImg(img);
        }
    }
}
