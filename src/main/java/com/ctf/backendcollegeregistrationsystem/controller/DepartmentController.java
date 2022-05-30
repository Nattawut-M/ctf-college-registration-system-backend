package com.ctf.backendcollegeregistrationsystem.controller;

import com.ctf.backendcollegeregistrationsystem.entity.Department;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.DepartmentException;
import com.ctf.backendcollegeregistrationsystem.model.DepartmentRequest;
import com.ctf.backendcollegeregistrationsystem.model.Response;
import com.ctf.backendcollegeregistrationsystem.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    ResponseEntity<Response> create(@RequestBody DepartmentRequest request) throws DefaultException {
        Department newDepartment = service.createByRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED.value(), newDepartment));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Response> updateById(@PathVariable("id") Long id, @RequestBody DepartmentRequest departmentRequest) throws DefaultException {
        return ResponseEntity.ok(new Response("Updated", service.updateById(id,   departmentRequest  )));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteByid(@PathVariable("id") Long id) throws DefaultException {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(new Response("deleted"));
    }
}
