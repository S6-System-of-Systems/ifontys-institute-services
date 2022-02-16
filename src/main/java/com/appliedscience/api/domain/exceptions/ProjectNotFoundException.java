package com.appliedscience.api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(Long id) {
        super("Project with id " + id + " not found");
    }
}
