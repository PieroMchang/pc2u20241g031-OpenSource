package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

/**
 * REST request resource for creating a FleetDispatch with nested value objects and waypoints.
 *
 * @author Piero Francisco Montes Chang
 */
public record CreateFleetDispatchResource(
        @NotBlank(message = "{validation.notBlank}")
        @Pattern(regexp = "^DSP-\\d{6}$", message = "{validation.dispatchCode.pattern}")
        String dispatchCode,

        @NotNull(message = "{validation.notNull}")
        UUID vehicleId,

        @NotNull(message = "{validation.notNull}")
        @Valid
        RouteDataResource routeData,

        @NotNull(message = "{validation.notNull}")
        @Valid
        TemporalWindowResource temporalWindow,

        @NotNull(message = "{validation.notNull}")
        @Valid
        EnergyProfileResource energyProfile,

        @NotNull(message = "{validation.notNull}")
        @Size(min = 1, max = 5, message = "{validation.waypoints.size}")
        @Valid
        Set<WaypointResource> waypoints,

        String status,

        String safetyNotes
) {
}