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

    public List<Faculty> createAll(List<Faculty> faculties) {

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

    public List<Faculty> findAll() {
        return repository.findAll();
    }

    public Faculty updateById(Long id, String updateName) throws FacultyException {
        Optional<Faculty> optFindById = repository.findById(id);
        if (optFindById.isEmpty()) {
            throw FacultyException.notFoundById(id);
        }

        Optional<Faculty> optDuplicatedName = repository.findByName(updateName);
        if (optDuplicatedName.isPresent() && !optDuplicatedName.get().getId().equals(id)) {
            throw FacultyException.duplicatedName(updateName);
        }

        Faculty faculty = optFindById.get();
        faculty.setName(updateName);

        return repository.save(faculty);
    }

    public void deleteById(Long id) throws DefaultException {
        Optional<Faculty> optionalFaculty = repository.findById(id);
        if (optionalFaculty.isEmpty()) {
            throw FacultyException.notFoundById(id);
        }

        repository.delete(optionalFaculty.get());
    }
}
