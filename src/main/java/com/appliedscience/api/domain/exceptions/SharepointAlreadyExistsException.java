package com.appliedscience.api.domain.exceptions;

public class SharepointAlreadyExistsException extends Exception {
    public SharepointAlreadyExistsException(String name) {
        super("Teacher with name " + name + " already exists");
    }
}
