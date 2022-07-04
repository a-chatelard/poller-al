package fr.gamedev.tags.service.exception;

public class ResourceConflictException extends Exception {
    public ResourceConflictException(Class<?> resourceType, Object resourceId) {
        super("A resource of type " + resourceType.getSimpleName() + " already exists for id " + resourceId + ".");
    }
}
