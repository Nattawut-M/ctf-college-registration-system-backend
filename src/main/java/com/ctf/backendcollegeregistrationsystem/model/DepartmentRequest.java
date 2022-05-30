package com.ctf.backendcollegeregistrationsystem.model;

import lombok.Data;

@Data
public class DepartmentRequest {
    private String name;
    private Long facultyId;
}
