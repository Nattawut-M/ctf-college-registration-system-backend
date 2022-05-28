package com.ctf.backendcollegeregistrationsystem.service;

import com.ctf.backendcollegeregistrationsystem.entity.Student;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.StudentException;
import com.ctf.backendcollegeregistrationsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public List<Student> createAll(List<Student> students) {
        return repository.saveAll(students);
    }

    public Student update(Student studentUpdate) throws DefaultException {
        Optional<Student> optional = repository.findById(studentUpdate.getId());
        if (optional.isEmpty()) {
            throw StudentException.notFoundById(studentUpdate.getId());
        }

        Student student = optional.get();
        student.setEmail(studentUpdate.getEmail());
        student.setFirstName(studentUpdate.getFirstName());
        student.setLastName(studentUpdate.getLastName());
        student.setDateOfBirth(studentUpdate.getDateOfBirth());

        return repository.save(student);
    }

    public void deleteById(String id) throws StudentException {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw StudentException.notFoundById(id);
        }

        repository.deleteById(id);
    }

    public Student findById(String id) throws StudentException {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw StudentException.notFoundById(id);
        }

        return optional.get();
    }

    public Student findByEmail(String email) throws DefaultException {
        Optional<Student> optional = repository.findByEmail(email);
        if (optional.isEmpty()) {
            throw StudentException.notFoundByEmail(email);
        }

        return optional.get();
    }

    public Student findByFirstName(String name) throws DefaultException {
        Optional<Student> optional = repository.findByFirstName(name);
        if (optional.isEmpty()) {
            throw StudentException.notFoundByName(name);
        }

        return optional.get();
    }

    public Student findByLastName(String name) throws DefaultException {
        Optional<Student> optional = repository.findByLastName(name);
        if (optional.isEmpty()) {
            throw StudentException.notFoundByName(name);
        }

        return optional.get();
    }
}
