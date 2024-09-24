package com.example.thundershop.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ThunderErrorResponse> hendlerException(ThunderException thunderException){

        ThunderErrorResponse errorResponse = new ThunderErrorResponse(thunderException.getMessage(), thunderException.getHttpStatus().value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, thunderException.getHttpStatus());

    }

    @ExceptionHandler
    public ResponseEntity<ThunderErrorResponse> handlerException(Exception exception){

        ThunderErrorResponse errorResponse = new ThunderErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
