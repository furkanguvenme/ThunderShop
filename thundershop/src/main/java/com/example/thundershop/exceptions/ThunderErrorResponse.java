package com.example.thundershop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ThunderErrorResponse {
    private String message;
    private int status;
    private LocalDateTime time;
}
