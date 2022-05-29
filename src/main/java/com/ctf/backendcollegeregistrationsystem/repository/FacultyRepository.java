package com.ctf.backendcollegeregistrationsystem.repository;

import com.ctf.backendcollegeregistrationsystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findByName(String name);
    List<Faculty> findAll();
}
