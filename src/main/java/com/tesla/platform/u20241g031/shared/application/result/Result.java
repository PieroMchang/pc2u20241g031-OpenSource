package com.tesla.platform.u20241g031.shared.application.result;

import java.util.Objects;

/**
 * Functional Result container representing either a successful operation with a value
 * or a failure with an {@link ApplicationError}.
 *
 * @param <T> type of the value returned on success
 * @author Piero Francisco Montes Chang
 */
public sealed interface Result<T> permits Result.Success, Result.Failure {

    /**
     * Creates a successful Result wrapping the given value.
     *
     * @param value result value
     * @param <T>   value type
     * @return {@link Success} instance
     */
    static <T> Result<T> success(T value) {
        return new Success<>(Objects.requireNonNull(value, "Success value must not be null."));
    }

    /**
     * Creates a failed Result wrapping the given {@link ApplicationError}.
     *
     * @param error application error
     * @param <T>   expected value type
     * @return {@link Failure} instance
     */
    static <T> Result<T> failure(ApplicationError error) {
        return new Failure<>(Objects.requireNonNull(error, "Failure error must not be null."));
    }

    /**
     * Successful result variant.
     *
     * @param value wrapped payload
     * @param <T>   value type
     */
    record Success<T>(T value) implements Result<T> {
    }

    /**
     * Failed result variant.
     *
     * @param error wrapped application error
     * @param <T>   expected value type
     */
    record Failure<T>(ApplicationError error) implements Result<T> {
    }
}