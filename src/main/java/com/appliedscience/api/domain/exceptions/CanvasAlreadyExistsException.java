package com.appliedscience.api.domain.exceptions;

public class CanvasAlreadyExistsException extends Exception {
    public CanvasAlreadyExistsException(String id) {
        super("Teacher with id " + id + " already exists");
    }
}
