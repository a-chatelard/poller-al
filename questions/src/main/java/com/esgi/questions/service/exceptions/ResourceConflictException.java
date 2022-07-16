package com.esgi.questions.service.exceptions;

public class ResourceConflictException extends Exception {
    public ResourceConflictException(final Class<?> resourceType, final Object resourceId) {
        super("A resource of type " + resourceType.getSimpleName() + " already exists for id " + resourceId + ".");
    }

    public ResourceConflictException(final String message) {
        super(message);
    }
}
