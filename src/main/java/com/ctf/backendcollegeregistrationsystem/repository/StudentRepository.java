package com.ctf.backendcollegeregistrationsystem.repository;

import com.ctf.backendcollegeregistrationsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEmail(String email);

    Optional<Student> findByFirstName(String s);

    Optional<Student> findByLastName(String s);

}
