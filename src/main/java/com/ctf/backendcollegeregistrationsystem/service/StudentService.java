package com.ctf.backendcollegeregistrationsystem.service;

import com.ctf.backendcollegeregistrationsystem.entity.Department;
import com.ctf.backendcollegeregistrationsystem.entity.Student;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.exception.StudentException;
import com.ctf.backendcollegeregistrationsystem.model.StudentRequest;
import com.ctf.backendcollegeregistrationsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final DepartmentService departmentService;


    public StudentService(StudentRepository repository, DepartmentService departmentService) {
        this.repository = repository;
        this.departmentService = departmentService;
    }

    public Student create(Student student) throws DefaultException {
        Optional<Student> optionalStudent = repository.findByEmail(student.getEmail());
        optionalStudent.orElseThrow(() -> StudentException.duplicatedEmail(student.getEmail()));
        return repository.save(student);
    }

    public Student createFromRequest(StudentRequest request) throws DefaultException {
//        check email is duplicated
        Optional<Student> optionalDuplicatedEmail = repository.findByEmail(request.getEmail());
        if (optionalDuplicatedEmail.isPresent()) {
            throw StudentException.duplicatedEmail(request.getEmail());
        }
//        find 'department' by departmentId from request
        Department departmentGetById = departmentService.findById(request.getDepartmentId());

//        create student -> save -> return 'saved student'
        Student student = new Student();
        student.setDepartment(departmentGetById);
        student.setEmail(request.getEmail());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDateOfBirth(request.getDateOfBirth());

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

    public Student updateByRequest(String id, StudentRequest request) throws DefaultException {
//        find student for update by id
        Optional<Student> optionalStudent = repository.findById(id);
//        if not found = throw exception
        Student student = optionalStudent.orElseThrow(() -> StudentException.notFoundById(id));

//        check new email is duplicate
        Optional<Student> optionalEmailDuplicated = repository.findByEmail(request.getEmail());
        if (optionalEmailDuplicated.isPresent()) {
            throw StudentException.duplicatedEmail(request.getEmail());
        }

//        find department by departmentId from request
        Department department = departmentService.findById(request.getDepartmentId());

//        update
        student.setEmail(request.getEmail());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDepartment(department);

        return repository.save(student);
    }

    public void deleteById(String id) throws StudentException {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw StudentException.notFoundById(id);
        }

        repository.deleteById(id);
    }

    public List<Student> findAll() {
        return repository.findAll();
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
