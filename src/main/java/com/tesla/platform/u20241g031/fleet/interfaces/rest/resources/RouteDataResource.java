package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Nested REST request resource for route data.
 *
 * @author Piero Francisco Montes Chang
 */
public record RouteDataResource(
        @NotNull(message = "{validation.notNull}")
        @Valid
        GeoCoordinatesResource originCoordinates,

        @NotNull(message = "{validation.notNull}")
        @Valid
        GeoCoordinatesResource destinationCoordinates,

        @NotNull(message = "{validation.notNull}")
        @DecimalMin(value = "0.0", inclusive = false, message = "{validation.distance.positive}")
        BigDecimal totalDistanceKm
) {
}