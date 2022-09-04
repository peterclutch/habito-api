package com.habito.platform.exception;

public class NotFoundException extends RuntimeException {

    public <T> NotFoundException(Class<T> clazz, Object id) {
        super(String.format("No %s was found for identifier: %s", clazz.getSimpleName(), id));
    }
}
