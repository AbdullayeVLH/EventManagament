package com.example.eventapp.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DateBetweenDto {
    private LocalDate firstDate;
    private LocalDate secondDate;
}
