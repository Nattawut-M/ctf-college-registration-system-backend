package com.ctf.backendcollegeregistrationsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "department")
    @Column
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(nullable = false, unique = true)
    private String name;


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    @Column
    private LocalDate updatedDate;

    public Department(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public Department() {

    }
}
