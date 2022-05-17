package com.appliedscience.api.domain.exceptions;

public class CanvasNotFoundException extends Exception {
    public CanvasNotFoundException(String id) {
        super("Teacher with id " + id + " not found");
    }
}
