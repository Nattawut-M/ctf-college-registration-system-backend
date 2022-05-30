package com.ctf.backendcollegeregistrationsystem.service;

import com.ctf.backendcollegeregistrationsystem.entity.Department;
import com.ctf.backendcollegeregistrationsystem.entity.Faculty;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.DepartmentException;
import com.ctf.backendcollegeregistrationsystem.model.DepartmentRequest;
import com.ctf.backendcollegeregistrationsystem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    private final FacultyService facultyService;


    public DepartmentService(DepartmentRepository repository, FacultyService facultyService) {
        this.repository = repository;
        this.facultyService = facultyService;
    }

    public Department createByRequest(DepartmentRequest request) throws DefaultException {

        Optional<Department> optionalDepartment = repository.findByName(request.getName());
        if (optionalDepartment.isPresent()) {
            throw DepartmentException.duplicatedByName(request.getName());
        }

        Faculty faculty = facultyService.findById(request.getFacultyId());
        Department department = new Department(request.getName(), faculty);

        return repository.save(department);
    }

    public Department createByObject(Department department) throws DefaultException {

        Optional<Department> optionalDepartment = repository.findByName(department.getName());
        if (optionalDepartment.isPresent()) {
            throw DepartmentException.duplicatedByName(department.getName());
        }

        return repository.save(department);
    }

    public Department findByName(String name) throws DepartmentException {
        Optional<Department> optional = repository.findByName(name);
        if (optional.isEmpty()) {
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

    public Department updateById(Long id, DepartmentRequest departmentRequest) throws DefaultException {
        Optional<Department> optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw DepartmentException.notFoundById(id);
        }


        Faculty faculty = facultyService.findById(departmentRequest.getFacultyId());
        Department department = optionalDepartment.get();
        department.setName(departmentRequest.getName());
        department.setFaculty(faculty);

        return repository.save(department);
    }

    public void deleteById(Long id) throws DepartmentException {
        Optional<Department> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw DepartmentException.notFoundById(id);
        } else {
            Department department = optional.get();
            repository.delete(department);
        }
    }
}
