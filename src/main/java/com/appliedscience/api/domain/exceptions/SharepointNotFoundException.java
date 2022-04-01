package com.appliedscience.api.domain.exceptions;

public class SharepointNotFoundException extends Exception {
    public SharepointNotFoundException(Long id) {
        super("Teacher with id " + id + " not found");
    }
}
