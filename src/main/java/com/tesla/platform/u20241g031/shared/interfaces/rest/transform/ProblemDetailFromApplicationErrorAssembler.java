package com.tesla.platform.u20241g031.shared.interfaces.rest.transform;

import com.tesla.platform.u20241g031.shared.application.result.ApplicationError;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Translates an {@link ApplicationError} into a localized RFC 7807 {@link ProblemDetail}.
 *
 * @author Piero Francisco Montes Chang
 */
@Component
public class ProblemDetailFromApplicationErrorAssembler {

    private final MessageSource messageSource;

    public ProblemDetailFromApplicationErrorAssembler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Maps an {@link ApplicationError} into a localized {@link ProblemDetail}.
     *
     * @param error application error
     * @return localized {@link ProblemDetail}
     */
    public ProblemDetail toProblemDetail(ApplicationError error) {
        Locale locale = LocaleContextHolder.getLocale();
        HttpStatus status = mapStatus(error.type());
        String titleKey = mapTitleKey(error.type());

        String title = messageSource.getMessage(titleKey, null, status.getReasonPhrase(), locale);
        String detail = messageSource.getMessage(error.messageKey(), error.args(), error.messageKey(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        return problemDetail;
    }

    private HttpStatus mapStatus(ApplicationError.ErrorType type) {
        return switch (type) {
            case VALIDATION -> HttpStatus.BAD_REQUEST;
            case CONFLICT -> HttpStatus.CONFLICT;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case INTERNAL -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private String mapTitleKey(ApplicationError.ErrorType type) {
        return switch (type) {
            case VALIDATION -> "error.bad.request.title";
            case CONFLICT -> "error.conflict.title";
            case NOT_FOUND -> "error.not.found.title";
            case INTERNAL -> "error.internal.title";
        };
    }
}