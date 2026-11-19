package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Nested REST request resource for an intermediate waypoint.
 *
 * @author Piero Francisco Montes Chang
 */
public record WaypointResource(
        @NotNull(message = "{validation.notNull}")
        @Positive(message = "{validation.waypoint.stopSequence}")
        Integer stopSequence,

        @NotBlank(message = "{validation.notBlank}")
        String locationName
) {
}