package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Nested REST request resource for geographic coordinates.
 *
 * @author Piero Francisco Montes Chang
 */
public record GeoCoordinatesResource(
        @NotNull(message = "{validation.notNull}")
        @DecimalMin(value = "-90.0", message = "{validation.coordinates.latitude}")
        @DecimalMax(value = "90.0", message = "{validation.coordinates.latitude}")
        Double latitude,

        @NotNull(message = "{validation.notNull}")
        @DecimalMin(value = "-180.0", message = "{validation.coordinates.longitude}")
        @DecimalMax(value = "180.0", message = "{validation.coordinates.longitude}")
        Double longitude
) {
}