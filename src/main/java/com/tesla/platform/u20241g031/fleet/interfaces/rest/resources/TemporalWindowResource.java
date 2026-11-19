package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Nested REST request resource for departure and estimated arrival times.
 *
 * @author Piero Francisco Montes Chang
 */
public record TemporalWindowResource(
        @NotNull(message = "{validation.notNull}")
        LocalDateTime departureTime,

        @NotNull(message = "{validation.notNull}")
        LocalDateTime estimatedArrival
) {
}