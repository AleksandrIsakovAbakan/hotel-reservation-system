package com.example.hotelreservationsystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatisticsEvent {

    private String typeEvent;

    private long userId;

    private LocalDate arrivalDate;

    private LocalDate dateOfDeparture;

}
