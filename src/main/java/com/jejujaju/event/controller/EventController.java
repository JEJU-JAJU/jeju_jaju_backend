package com.jejujaju.event.controller;

import com.jejujaju.event.model.dto.Event;
import com.jejujaju.event.model.dto.EventBadgeDto;
import com.jejujaju.event.model.dto.EventImg;
import com.jejujaju.event.model.dto.EventResponseDto;
import com.jejujaju.event.model.service.EventService;
import com.jejujaju.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<?> saveEvent(@AuthenticationPrincipal User user,
                                       @RequestParam("planId") Long planId,
                                       @RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate,
                                       @RequestParam(value = "description", required = false) String description,
                                       @RequestParam(value = "badgeImg", required = false) String badgeImg,
                                       @RequestParam(value = "files", required = false) List<MultipartFile> files) throws Exception {

        Timestamp start = Timestamp.valueOf(LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Timestamp end = Timestamp.valueOf(LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        Event event = Event.builder().userId(user.getUserId())
                .planId(planId)
                .startDate(start)
                .endDate(end)
                .description(description)
                .badgeImg(badgeImg).build();
        eventService.saveEvent(event, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvent(){
        List<Event> eventList = eventService.findAllEvent();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<EventResponseDto> findEventByEventId(@PathVariable("event-id") Long eventId) throws Exception {
        Event event = eventService.findEventByEventId(eventId);
        List<EventImg> eventImgList = eventService.findEventImgByEventId(eventId);
        return new ResponseEntity<>(EventResponseDto.builder().event(event).eventImgList(eventImgList).build(), HttpStatus.OK);
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<Void> modifyEvent(@PathVariable("event-id") Long eventId, @RequestBody Event event) {
        event.setEventId(eventId);
        eventService.modifyEvent(event);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("event-id") Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/badges/my")
    public ResponseEntity<List<EventBadgeDto>> findBadgesByUserId(@AuthenticationPrincipal User user){
        List<EventBadgeDto> badgeList = eventService.findBadgesByUserId(user.getUserId());
        return new ResponseEntity<>(badgeList, HttpStatus.OK);
    }
}
