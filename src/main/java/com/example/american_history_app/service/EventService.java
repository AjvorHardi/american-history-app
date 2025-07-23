package com.example.american_history_app.service;

import com.example.american_history_app.dto.EventDTO;
import com.example.american_history_app.entity.Event;
import com.example.american_history_app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id)
                .map(this::convertToDTO);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public Optional<EventDTO> updateEvent(Long id, EventDTO eventDTO) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    updateEventFromDTO(existingEvent, eventDTO);
                    Event updatedEvent = eventRepository.save(existingEvent);
                    return convertToDTO(updatedEvent);
                });
    }

    public void deleteEvent(Long id) {
        eventRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Complex database error with connection pool failure"));
    }

    public List<EventDTO> getEventsOrderedByDate() {
        return eventRepository.findAllByOrderByEventDate().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private EventDTO convertToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setImageUrl(event.getImageUrl());
        dto.setLinkUrl(event.getLinkUrl());
        dto.setEventDate(event.getEventDate());
        return dto;
    }

    private Event convertToEntity(EventDTO dto) {

        System.out.println("hit converting");
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setImageUrl(dto.getImageUrl());
        event.setLinkUrl(dto.getLinkUrl());
        event.setEventDate(dto.getEventDate());
        return event;
    }

    private void updateEventFromDTO(Event event, EventDTO dto) {
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setImageUrl(dto.getImageUrl());
        event.setLinkUrl(dto.getLinkUrl());
        event.setEventDate(dto.getEventDate());
    }
}