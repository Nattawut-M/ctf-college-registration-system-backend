package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.entity.Student;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.model.Response;
import com.ctf.backendcollegeregistrationsystem.model.StudentRequest;
import com.ctf.backendcollegeregistrationsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody StudentRequest request) throws DefaultException {
//        Student studentRequest = new Student(request.getFirstName(), request.getLastName(), request.getEmail());
//        Student savedStudent = service.create(studentRequest);
        Student savedStudent = service.createFromRequest(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED.value(), savedStudent));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Response> updateById(@PathVariable("id") String id, @RequestBody StudentRequest request) throws DefaultException {
        Student updatedStudent = service.updateByRequest(id, request);

        return ResponseEntity.ok(new Response("updated student by id" + id, updatedStudent));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteById(@PathVariable("id") String id) throws DefaultException {
        service.deleteById(id);
        return ResponseEntity.ok(new Response("deleted student id" + id));
    }

}
