package com.example.eventapp.controllers;

import com.example.eventapp.dtos.AddEventDto;
import com.example.eventapp.dtos.DateBetweenDto;
import com.example.eventapp.exceptions.EventAlreadyExist;
import com.example.eventapp.models.Event;
import com.example.eventapp.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EventController {
    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ExceptionHandler(EventAlreadyExist.class)
    public ResponseEntity<String> handleNotFound(EventAlreadyExist e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> addEvent (@RequestBody AddEventDto addEventDto){
        return ResponseEntity.ok(eventService.addEvent(addEventDto));
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody AddEventDto addEventDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.updateEvent(addEventDto, id));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable("id") Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEventsDateBetween(@RequestBody DateBetweenDto dates){
        return ResponseEntity.ok(eventService.getEventsDateBetween(dates));
    }
}
