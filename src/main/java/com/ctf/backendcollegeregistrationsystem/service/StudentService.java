package com.ctf.backendcollegeregistrationsystem.service;

import com.ctf.backendcollegeregistrationsystem.entity.Student;
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

    public Student update(Student studentUpdate) {
        Optional<Student> optional = repository.findById(studentUpdate.getId());
        if (optional.isEmpty()) {
//            TODO: throw exception not found by id
            return null;
        }

        Student student = optional.get();
        student.setEmail(studentUpdate.getEmail());
        student.setFirstName(studentUpdate.getFirstName());
        student.setLastName(studentUpdate.getLastName());
        student.setDateOfBirth(studentUpdate.getDateOfBirth());

        return repository.save(student);
    }

    public void deleteById(String id) {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) {
//            TODO: throw exception not found by id
        }

        repository.deleteById(id);
    }

    public Student findById(String id) {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) {
//            TODO; throw exception
            return null;
        }

        return optional.get();
    }

    public Student findByEmail(String email) {
        Optional<Student> optional = repository.findByEmail(email);
        if (optional.isEmpty()) {
//            TODO; throw exception
//            return null;
        }

        return optional.get();
    }

    public Student findByFirstName(String name) {
        Optional<Student> optional = repository.findByFirstName(name);
        if (optional.isEmpty()) {
//            TODO; throw exception
            return null;
        }

        return optional.get();
    }

    public Student findByLastName(String name) {
        Optional<Student> optional = repository.findByLastName(name);
        if (optional.isEmpty()) {
//            TODO; throw exception
            return null;
        }

        return optional.get();
    }
}
