package com.example.eventapp.services;

import com.example.eventapp.dtos.AddEventDto;
import com.example.eventapp.dtos.DateBetweenDto;
import com.example.eventapp.models.Event;

import java.util.List;

public interface EventService {

    Event addEvent(AddEventDto eventDto);

    Event  updateEvent(AddEventDto updateEventDto, Long id);

    String deleteEvent(Long id);

    Event getEvent(Long id);

    List<Event> getAllEvents();

    List<Event> getEventsDateBetween(DateBetweenDto dates);
}
