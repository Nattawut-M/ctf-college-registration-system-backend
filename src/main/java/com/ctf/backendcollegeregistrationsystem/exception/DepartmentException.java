package com.ctf.backendcollegeregistrationsystem.exception;

public class DepartmentException extends DefaultException{
    public DepartmentException(String message) {
        super("department: " +  message);
    }

    public static DepartmentException notFoundById(Long id) {
        return new DepartmentException(String.format("id %s not found.", id));
    }

    public static DepartmentException notFoundByName(String name) {
        return new DepartmentException(String.format("department name %s not found.", name));
    }

    public static DepartmentException duplicatedByName(String name) {
        return new DepartmentException(name + "is duplicated, please try another");
    }
}
