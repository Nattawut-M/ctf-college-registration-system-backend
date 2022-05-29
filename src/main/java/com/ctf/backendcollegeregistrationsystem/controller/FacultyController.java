package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.model.Response;
import com.ctf.backendcollegeregistrationsystem.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
