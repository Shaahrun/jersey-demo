package com.gmail.ejikemesharon;

public class ResourceNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public ResourceNotFoundException() {

    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
