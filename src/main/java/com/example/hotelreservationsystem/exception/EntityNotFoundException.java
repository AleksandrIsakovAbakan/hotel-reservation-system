package com.example.hotelreservationsystem.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
