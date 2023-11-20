package com.jejujaju.event.controller;

import com.jejujaju.event.model.dto.Event;
import com.jejujaju.event.model.service.EventService;
import com.jejujaju.review.model.dto.ReviewRequestDto;
import com.jejujaju.review.model.dto.ReviewResponseDto;
import com.jejujaju.review.model.dto.ReviewSaveDto;
import com.jejujaju.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Void> saveEvent(@AuthenticationPrincipal User user, @RequestBody Event event) {
        event.setUserId(user.getUserId());
        eventService.saveEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvent(){
        List<Event> eventList = eventService.findAllEvent();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<Event> findEventByEventId(@PathVariable("event-id") Long eventId){
        Event event = eventService.findEventByEventId(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
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
}
