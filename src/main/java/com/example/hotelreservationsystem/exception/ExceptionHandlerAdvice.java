package com.example.hotelreservationsystem.exception;

import com.example.hotelreservationsystem.api.v1.response.ErrorRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.net.BindException;
import java.util.Date;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorRs> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorRs> handleBindException(BindException e) {
        return ResponseEntity.badRequest().body(makeErrors("BindException", e));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorRs> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(makeErrors("HttpMessageNotReadableException", e));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorRs> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(makeErrors("MethodArgumentNotValidException", e));
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorRs> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(makeErrors("MissingServletRequestParameterException", e));
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    public ResponseEntity<ErrorRs> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return ResponseEntity.badRequest().body(makeErrors("MissingServletRequestPartException", e));
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public ResponseEntity<ErrorRs> handleConversionNotSupportedException(ConversionNotSupportedException e) {
        return ResponseEntity.internalServerError().body(makeErrors("ConversionNotSupportedException", e));
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<ErrorRs> handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        return ResponseEntity.internalServerError().body(makeErrors("HttpMessageNotWritableException", e));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorRs> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.internalServerError().body(makeErrors("NoSuchElementException", e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRs> globalExceptionHandling(Exception exception, WebRequest request) {
        return ResponseEntity.internalServerError().body(makeErrors("Exception", exception));
    }

    @ExceptionHandler(AlreadySuchNameException.class)
    public ResponseEntity<ErrorRs> handleAlreadySuchNameException(
            AlreadySuchNameException ex) {
        log.info("AlreadySuchNameException " + ex);
        return ResponseEntity.badRequest().body(makeErrors("AlreadySuchNameException", ex));
    }

    private ErrorRs makeErrors(String error, Exception e){

        ErrorRs errorRs = new ErrorRs();
        errorRs.setError(error);
        errorRs.setErrorDescription(e.getMessage());
        errorRs.setTimestamp(System.currentTimeMillis());

        return errorRs;
    }

}
