package com.example.hotelreservationsystem.exception;

import com.example.hotelreservationsystem.api.v1.response.ErrorRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorRs> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(makeErrors("EntityNotFoundException", e));
    }

    private ErrorRs makeErrors(String error, Exception e){

        ErrorRs errorRs = new ErrorRs();
        errorRs.setError(error);
        errorRs.setErrorDescription(e.getMessage());
        errorRs.setTimestamp(System.currentTimeMillis());

        return errorRs;
    }

}
