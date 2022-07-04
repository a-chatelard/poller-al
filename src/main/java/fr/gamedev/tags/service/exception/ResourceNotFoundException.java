package fr.gamedev.tags.service.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(Class<?> resourceType) {
        super(resourceType.getSimpleName() + " not found.");
    }

    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(resourceName + " " + resourceId + " not found.");
    }

    public ResourceNotFoundException(Class<?> resourceType, Object resourceId) {
        super(resourceType.getSimpleName() + " " + resourceId + " not found.");
    }
}
