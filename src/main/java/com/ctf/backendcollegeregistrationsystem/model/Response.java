package com.ctf.backendcollegeregistrationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int status = 200;
    private String message;
    private Object payload;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Response(String message) {
        this.message = message;
    }

    public Response(Object payload) {
        this.payload = payload;
    }

    public Response(String message, Object payload) {
        this.message = message;
        this.payload = payload;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }
}
