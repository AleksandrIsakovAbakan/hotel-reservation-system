package com.example.hotelreservationsystem.exception;

public class DuringSpecifiedPeriodRoomOccupiedException extends RuntimeException{

    public DuringSpecifiedPeriodRoomOccupiedException(String errorMessage) {
        super(errorMessage);
    }
}
