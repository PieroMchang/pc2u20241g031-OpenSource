package com.tesla.platform.u20241g031.shared.interfaces.rest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Centralized REST exception handler producing localized RFC 7807 {@link ProblemDetail} responses
 * honoring the Accept-Language request header.
 *
 * @author Piero Francisco Montes Chang
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Handles Jakarta Bean Validation failures on request payloads.
     *
     * @param ex validation exception
     * @return HTTP 400 Bad Request with field-level error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationException(MethodArgumentNotValidException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String title = messageSource.getMessage("error.validation.title", null, "Validation Error", locale);
        String detail = messageSource.getMessage("error.validation.detail", null, "Validation failed.", locale);

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
        problemDetail.setTitle(title);
        problemDetail.setProperty("errors", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    /**
     * Handles domain invariant violations thrown as {@link IllegalArgumentException}.
     *
     * @param ex domain argument exception
     * @return HTTP 400 Bad Request with localized error detail
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String title = messageSource.getMessage("error.bad.request.title", null, "Bad Request", locale);
        String detail = resolveLocalizedDomainMessage(ex.getMessage(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
        problemDetail.setTitle(title);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    /**
     * Handles malformed JSON payloads or unparseable types such as invalid UUIDs or dates.
     *
     * @param ex message readability exception
     * @return HTTP 400 Bad Request with localized message
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleUnreadableMessage(HttpMessageNotReadableException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String title = messageSource.getMessage("error.bad.request.title", null, "Bad Request", locale);
        String detail = messageSource.getMessage(
                "error.malformed.json",
                null,
                "Malformed JSON request or invalid data format.",
                locale
        );

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
        problemDetail.setTitle(title);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    private String resolveLocalizedDomainMessage(String rawMessage, Locale locale) {
        if (rawMessage == null) {
            return messageSource.getMessage("error.bad.request.title", null, "Bad Request", locale);
        }
        String lower = rawMessage.toLowerCase(Locale.ROOT);
        if (lower.contains("estimated arrival")) {
            return messageSource.getMessage("fleet.dispatch.invalid.temporalWindow", null, rawMessage, locale);
        }
        if (lower.contains("dispatch code")) {
            return messageSource.getMessage("fleet.dispatch.invalid.code", null, rawMessage, locale);
        }
        if (lower.contains("vehicle id")) {
            return messageSource.getMessage("fleet.dispatch.invalid.vehicleId", null, rawMessage, locale);
        }
        if (lower.contains("latitude") || lower.contains("longitude")) {
            return messageSource.getMessage("fleet.dispatch.invalid.coordinates", null, rawMessage, locale);
        }
        if (lower.contains("distance")) {
            return messageSource.getMessage("fleet.dispatch.invalid.distance", null, rawMessage, locale);
        }
        if (lower.contains("kwh") || lower.contains("weather multiplier")) {
            return messageSource.getMessage("fleet.dispatch.invalid.energyProfile", null, rawMessage, locale);
        }
        if (lower.contains("waypoint")) {
            return messageSource.getMessage("fleet.dispatch.invalid.waypoints", null, rawMessage, locale);
        }
        if (lower.contains("dispatch status")) {
            return messageSource.getMessage("fleet.dispatch.invalid.status", null, rawMessage, locale);
        }
        return messageSource.getMessage(rawMessage, null, rawMessage, locale);
    }
}