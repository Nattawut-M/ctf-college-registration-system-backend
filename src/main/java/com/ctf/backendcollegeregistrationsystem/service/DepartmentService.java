package com.ctf.backendcollegeregistrationsystem.service;

import com.ctf.backendcollegeregistrationsystem.entity.Department;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.DepartmentException;
import com.ctf.backendcollegeregistrationsystem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department create(Department department) throws DefaultException {
        Optional<Department> optionalDepartment = repository.findByName(department.getName());
        if (optionalDepartment.isPresent()) {
            throw DepartmentException.duplicatedByName(department.getName());
        }

        return repository.save(department);
    }

    public Department findByName(String name) throws DepartmentException {
        Optional<Department> optional = repository.findByName(name);
        if (optional.isEmpty()){
            throw DepartmentException.notFoundByName(name);
        }

        return optional.get();
    }

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Department findById(Long id) throws DepartmentException {
        Optional<Department> optionalDepartment = repository.findById(id);
        return optionalDepartment.orElseThrow(() -> DepartmentException.notFoundById(id));
    }
}
