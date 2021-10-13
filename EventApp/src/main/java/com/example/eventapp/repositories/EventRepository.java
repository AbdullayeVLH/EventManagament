package com.example.eventapp.repositories;

import com.example.eventapp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByDate(LocalDate date);

    List<Event> findAllByDateBetween(LocalDate firstDate, LocalDate secondDate);
}
