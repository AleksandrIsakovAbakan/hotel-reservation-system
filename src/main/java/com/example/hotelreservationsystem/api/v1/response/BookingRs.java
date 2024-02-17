package com.example.hotelreservationsystem.api.v1.response;

import com.example.hotelreservationsystem.entity.Room;
import com.example.hotelreservationsystem.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRs {

    private long id;

    private long userId;

    private long roomId;

    private LocalDate arrivalDate;

    private LocalDate dateOfDeparture;

    private User user;

    private Room room;
}
