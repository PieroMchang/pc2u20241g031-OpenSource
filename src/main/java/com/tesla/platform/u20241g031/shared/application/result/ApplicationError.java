package com.tesla.platform.u20241g031.shared.application.result;

/**
 * Represents a structured application-layer error supporting i18n message keys and arguments.
 *
 * @param type       error category used to map to HTTP status codes
 * @param messageKey i18n message key or fallback message
 * @param args       optional formatting arguments for message interpolation
 * @author Piero Francisco Montes Chang
 */
public record ApplicationError(ErrorType type, String messageKey, Object... args) {

    /**
     * Supported application error categories.
     */
    public enum ErrorType {
        VALIDATION,
        CONFLICT,
        NOT_FOUND,
        INTERNAL
    }

    /**
     * Creates a validation / bad request error.
     *
     * @param messageKey message key or text
     * @param args       optional arguments
     * @return validation {@link ApplicationError}
     */
    public static ApplicationError validation(String messageKey, Object... args) {
        return new ApplicationError(ErrorType.VALIDATION, messageKey, args);
    }

    /**
     * Creates a conflict error (e.g., duplicate business key).
     *
     * @param messageKey message key or text
     * @param args       optional arguments
     * @return conflict {@link ApplicationError}
     */
    public static ApplicationError conflict(String messageKey, Object... args) {
        return new ApplicationError(ErrorType.CONFLICT, messageKey, args);
    }

    /**
     * Creates a not-found error.
     *
     * @param messageKey message key or text
     * @param args       optional arguments
     * @return not-found {@link ApplicationError}
     */
    public static ApplicationError notFound(String messageKey, Object... args) {
        return new ApplicationError(ErrorType.NOT_FOUND, messageKey, args);
    }
}