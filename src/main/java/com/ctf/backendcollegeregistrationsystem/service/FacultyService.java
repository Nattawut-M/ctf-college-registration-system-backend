package com.ctf.backendcollegeregistrationsystem.service;

import com.ctf.backendcollegeregistrationsystem.entity.Faculty;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.FacultyException;
import com.ctf.backendcollegeregistrationsystem.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty create(Faculty faculty) throws DefaultException {
        Optional<Faculty> optionalDuplicatedName = repository.findByName(faculty.getName());
        if (optionalDuplicatedName.isPresent()) {
            throw FacultyException.duplicatedName(faculty.getName());
        }

        return repository.save(faculty);
    }

    public List<Faculty> createAll(List<Faculty> faculties) throws FacultyException {

        return repository.saveAll(faculties);

    }

    public Faculty findById(Long id) throws DefaultException {
        Optional<Faculty> facultyOptional = repository.findById(id);
        if (facultyOptional.isEmpty()) {
            throw FacultyException.notFoundById(id);
        }

        return facultyOptional.get();
    }

    public Faculty findByName(String name) throws DefaultException {
        Optional<Faculty> optionalFaculty = repository.findByName(name);
        if (optionalFaculty.isEmpty()) {
            throw FacultyException.notFoundByName(name);
        }

        return optionalFaculty.get();
    }

}
