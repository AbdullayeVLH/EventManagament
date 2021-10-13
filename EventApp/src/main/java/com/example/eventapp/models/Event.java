package com.example.eventapp.models;

import com.example.eventapp.dtos.AddEventDto;
import com.example.eventapp.enums.Color;
import com.example.eventapp.enums.Status;
import com.example.eventapp.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private Type type;
    private Status status;
    private LocalDateTime creationDate;
    private Color color;
    private Color dateColor;

    public Event(AddEventDto dto){
        this.name = dto.getName();
        this.date = dto.getDate();
        this.type = dto.getType();
    }
}
