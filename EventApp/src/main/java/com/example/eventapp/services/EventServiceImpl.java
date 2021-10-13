package com.example.eventapp.services;

import com.example.eventapp.dtos.AddEventDto;
import com.example.eventapp.dtos.DateBetweenDto;
import com.example.eventapp.enums.Color;
import com.example.eventapp.enums.Status;
import com.example.eventapp.enums.Type;
import com.example.eventapp.exceptions.EventAlreadyExist;
import com.example.eventapp.models.Event;
import com.example.eventapp.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class EventServiceImpl implements EventService{

    private EventRepository eventRepo;

    @Autowired
    public EventServiceImpl(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public Event addEvent(AddEventDto eventDto) {
        List<Event> events = eventRepo.findEventsByDate(eventDto.getDate());
        for (Event event: events){
            if (event.getName().equals(eventDto.getName())){
                throw new EventAlreadyExist();
            }else {
                Event newEvent = Event.builder()
                        .name(eventDto.getName())
                        .date(eventDto.getDate())
                        .status(Status.ADDED)
                        .type(eventDto.getType())
                        .creationDate(LocalDateTime.now())
                        .build();
                if (newEvent.getType().equals(Type.MOURNING)){
                    newEvent.toBuilder().color(Color.BLACK).build();
                }else {
                    newEvent.toBuilder().color(Color.GREEN).build();
                }
                eventRepo.save(newEvent);
            }
        }
        return null;
    }

    @Override
    public Event updateEvent(AddEventDto updateEventDto, Long id) {
        Event event = eventRepo.getById(id);
        event.toBuilder()
                .date(updateEventDto.getDate())
                .type(updateEventDto.getType())
                .name(updateEventDto.getName())
                .status(Status.UPDATED)
                .creationDate(LocalDateTime.now())
                .build();
        if (event.getType().equals(Type.MOURNING)){
            event.toBuilder().color(Color.BLACK).build();
        }else {
            event.toBuilder().color(Color.GREEN).build();
        }
        eventRepo.save(event);
        return event;
    }

    @Override
    public String deleteEvent(Long id) {
        eventRepo.deleteById(id);
        return "Event deleted";
    }

    @Override
    public Event getEvent(Long id) {
        Event event = eventRepo.getById(id);
        event.toBuilder().status(Status.SEEN).build();
        changeColor(event);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = eventRepo.findAll();
        changeColors(events);
        return events;
    }

    @Override
    public List<Event> getEventsDateBetween(DateBetweenDto dates) {
        List<Event> events = eventRepo.findAllByDateBetween(dates.getFirstDate(), dates.getSecondDate());
        changeColors(events);
        return events;
    }

    private void changeColors(List<Event> events){
        for (Event event: events){
            changeColor(event);
        }
    }

    private void changeColor(Event event){
        long diff = ChronoUnit.DAYS.between(event.getDate(), LocalDate.now());
        if (diff<=5){
            event.toBuilder().dateColor(Color.RED).build();
        }else if (diff<=10){
            event.toBuilder().dateColor(Color.ORANGE).build();
        }else if (diff<=20){
            event.toBuilder().dateColor(Color.YELLOW).build();
        }else {
            event.toBuilder().dateColor(Color.BLUE).build();
        }
    }
}
