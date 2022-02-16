package com.appliedscience.api.domain.exceptions;

public class TeacherAlreadyExistsException extends Exception {
    public TeacherAlreadyExistsException(String name) {
        super("Teacher with name " + name + " already exists");
    }
}
