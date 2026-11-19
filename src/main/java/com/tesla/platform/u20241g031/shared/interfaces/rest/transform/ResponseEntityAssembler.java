package com.tesla.platform.u20241g031.shared.interfaces.rest.transform;

import com.tesla.platform.u20241g031.shared.application.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Translates application-layer {@link Result} objects into HTTP {@link ResponseEntity} responses.
 *
 * @author Piero Francisco Montes Chang
 */
@Component
public class ResponseEntityAssembler {

    private final ProblemDetailFromApplicationErrorAssembler problemDetailAssembler;

    public ResponseEntityAssembler(ProblemDetailFromApplicationErrorAssembler problemDetailAssembler) {
        this.problemDetailAssembler = problemDetailAssembler;
    }

    /**
     * Converts a {@link Result} into an HTTP 201 Created response on success,
     * or a localized {@link ProblemDetail} response on failure.
     *
     * @param result         application result
     * @param resourceMapper function mapping the domain value to a REST resource
     * @param <T>            domain type
     * @param <R>            REST resource type
     * @return HTTP {@link ResponseEntity}
     */
    public <T, R> ResponseEntity<?> toCreatedResponse(Result<T> result, Function<T, R> resourceMapper) {
        return switch (result) {
            case Result.Success<T>(var value) ->
                    ResponseEntity.status(HttpStatus.CREATED).body(resourceMapper.apply(value));
            case Result.Failure<T>(var error) -> {
                ProblemDetail problemDetail = problemDetailAssembler.toProblemDetail(error);
                yield ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
            }
        };
    }
}