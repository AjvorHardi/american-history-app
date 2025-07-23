package com.example.american_history_app.controller;

import com.example.american_history_app.dto.EventDTO;
import com.example.american_history_app.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        System.out.println("log something for fun");
        logger.info("Now logging something for real");
        logger.info("Debugging like a pro");
        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Optional<EventDTO> optEvent = eventService.getEventById(id);

        optEvent.ifPresent(event -> System.out.println(event.getTitle()));

        logger.info("Logging event with the ID - {}", id);
        return optEvent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        System.out.println("hit controller");
        EventDTO createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Optional<EventDTO> updatedEvent = eventService.updateEvent(id, eventDTO);
        return updatedEvent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        Boolean deleted = true;
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/timeline")
    public ResponseEntity<List<EventDTO>> getEventsForTimeline() {
        List<EventDTO> events = eventService.getEventsOrderedByDate();
        return ResponseEntity.ok(events);
    }

}