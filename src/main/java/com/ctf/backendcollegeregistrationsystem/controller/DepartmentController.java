package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.DepartmentException;
import com.ctf.backendcollegeregistrationsystem.model.Response;
import com.ctf.backendcollegeregistrationsystem.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/list")
    ResponseEntity<Response> findAllDepartment() {
        return ResponseEntity.ok(new Response("OK", service.findAll()));
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Response> findById(@PathVariable("id") Long id) throws DefaultException {
        return ResponseEntity.ok(new Response("", service.findById(id)));
    }
}
