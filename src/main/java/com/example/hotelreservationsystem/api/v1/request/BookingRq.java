package com.example.hotelreservationsystem.api.v1.request;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class BookingRq {

    private long id;

    private long userId;

    private long roomId;

    @NonNull
    private LocalDate arrivalDate;

    @NonNull
    private LocalDate dateOfDeparture;
}
