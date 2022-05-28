package com.ctf.backendcollegeregistrationsystem.exception;

public class StudentException extends DefaultException {
    public StudentException(String message) {
        super("student: " + message);
    }

    public static StudentException notFoundById(String id) {
        return new StudentException(String.format("student id %s not found.", id));
    }

    public static StudentException notFoundByEmail(String email) {
        return new StudentException(String.format("student email %s not found.", email));
    }

    public static StudentException notFoundByName(String name) {
        return new StudentException(String.format("student %s not found.", name));
    }

}
