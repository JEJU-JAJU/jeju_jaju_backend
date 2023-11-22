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
    public List<EventImgResponseDto> findEventImgByEventId(Long eventId) throws IOException {
        List<EventImg> imgList = eventImgMapper.selectEventByEventId(eventId);
        List<EventImgResponseDto> eventImgList = new ArrayList<>();
        for(EventImg img : imgList){
            EventImgResponseDto eventImg = EventImgResponseDto.builder()
                                    .originalFileName(img.getOriginalFileName())
                                    .imageData(readImageData(img.getStoredFilePath()))
                                    .build();
            eventImgList.add(eventImg);
        }
        return eventImgList;
    }

    @Override
    public void modifyEvent(Event event) {
        eventMapper.updateEvent(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventMapper.deleteEvent(eventId);
    }

    private byte[] readImageData(String path) throws IOException {
        // 이미지 파일 경로 설정 (실제로는 여러 방법으로 이미지를 읽어올 수 있음)
        Path imagePath = Paths.get(path);
        return Files.readAllBytes(imagePath);
    }
}
