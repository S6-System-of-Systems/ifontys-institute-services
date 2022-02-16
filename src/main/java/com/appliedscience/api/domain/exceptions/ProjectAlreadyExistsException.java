package com.appliedscience.api.domain.exceptions;

public class ProjectAlreadyExistsException extends Exception {
    public ProjectAlreadyExistsException(String name) {
        super("Project with name " + name + " already exists");
    }
}
