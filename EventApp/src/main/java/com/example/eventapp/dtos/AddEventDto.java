package com.example.eventapp.dtos;

import com.example.eventapp.enums.Type;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddEventDto {
    private String name;
    private LocalDate date;
    private Type type;
}
