package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.StudentException;
import com.ctf.backendcollegeregistrationsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;


    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> findAllStudent() {
        return ResponseEntity.ok(Map.of("status", HttpStatus.OK.value(), "payload", service.findAll(), "timestamp", LocalDateTime.now()));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") String id) throws DefaultException {
        return ResponseEntity.ok(Map.of("status", HttpStatus.OK.value(), "payload", service.findById(id), "timestamp", LocalDateTime.now()));
    }
}
