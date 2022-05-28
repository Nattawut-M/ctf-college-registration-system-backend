package com.ctf.backendcollegeregistrationsystem.repository;

import com.ctf.backendcollegeregistrationsystem.entity.Department;
import com.ctf.backendcollegeregistrationsystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    List<Department> findByFaculty(Faculty faculty);
}
