package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> response(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                status.value(),
                e.getMessage()
        );

        return ResponseEntity.status(status).body(response);
    }

    @Data
    @NoArgsConstructor
    private static class ExceptionResponse {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String message;

        public ExceptionResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
