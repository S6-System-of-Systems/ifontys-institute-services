package com.appliedscience.api.domain.exceptions;

public class TeacherNotFoundException extends Exception {
    public TeacherNotFoundException(Long id) {
        super("Teacher with id " + id + " not found");
    }
}
