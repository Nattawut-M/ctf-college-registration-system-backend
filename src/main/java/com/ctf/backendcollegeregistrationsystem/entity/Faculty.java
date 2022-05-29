package com.ctf.backendcollegeregistrationsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "faculty")
    @Column
    @JsonBackReference
    private List<Department> departments;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    @UpdateTimestamp
    private LocalDate updatedDate;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(String name, List<Department> departments) {
        this.departments = departments;
        this.name = name;
    }
}
