package com.ctf.backendcollegeregistrationsystem.exception;

public class FacultyException extends DefaultException {
    public FacultyException(String message) {
        super("faculty: " + message);
    }

    public static FacultyException notFoundById(Long id) {
        return new FacultyException(String.format("id %s not found.", id));
    }

    public static FacultyException notFoundByName(String name) {
        return new FacultyException(String.format("name '%s' not found.", name));
    }

    public static FacultyException duplicatedName(String name) {
        return new FacultyException(name + "is duplicated, try another");
    }
}
