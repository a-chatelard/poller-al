package com.esgi.tags.service.exception;

public class ResourceConflictException extends Exception {
    public ResourceConflictException(final Class<?> resourceType, final Object resourceId) {
        super("A resource of type " + resourceType.getSimpleName() + " already exists for id " + resourceId + ".");
    }
}
