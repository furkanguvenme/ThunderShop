package com.example.thundershop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ThunderException extends RuntimeException{
    private HttpStatus httpStatus;

    public ThunderException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
