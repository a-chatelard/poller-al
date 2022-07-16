package com.esgi.questions.service.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(final Class<?> resourceType) {
        super(resourceType.getSimpleName() + " not found.");
    }

    public ResourceNotFoundException(final String resourceName, final Object resourceId) {
        super(resourceName + " " + resourceId + " not found.");
    }

    public ResourceNotFoundException(final Class<?> resourceType, final Object resourceId) {
        super(resourceType.getSimpleName() + " " + resourceId + " not found.");
    }
}
