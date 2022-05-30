package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.entity.Faculty;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.model.FacultyRequest;
import com.ctf.backendcollegeregistrationsystem.model.Response;
import com.ctf.backendcollegeregistrationsystem.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping(value = "/list")
    ResponseEntity<Response> findAllFaculty() {
        return ResponseEntity.ok(new Response(service.findAll()));
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Response> findById(@PathVariable("id") Long id) throws DefaultException {
        return ResponseEntity.ok(new Response(service.findById(id)));
    }

    @PostMapping("/create")
    ResponseEntity<Response> create(@RequestBody FacultyRequest request) throws DefaultException {
        Faculty newFaculty = service.create(new Faculty(request.getName()));

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new Response(HttpStatus.CREATED.value(), "created", newFaculty));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Response> updateById(@PathVariable("id") Long id, @RequestBody FacultyRequest request) throws DefaultException {
        Faculty updatedFaculty = service.updateById(id, request.getName());

        return ResponseEntity.ok(new Response("updated", updatedFaculty));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteById(@PathVariable("id") Long id) throws DefaultException {
        service.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(new Response("deleted"));
    }

}
