package com.example.hotelreservationsystem.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEvent {

    private long userId;

    private String username;

    private LocalDateTime checkInTime;
}
