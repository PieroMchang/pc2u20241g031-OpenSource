package com.tesla.platform.u20241g031.fleet.domain.model.valueobjects;

import java.util.UUID;

/**
 * Value Object encapsulating the external vehicle UUID from the Vehicles bounded context.
 *
 * @param value non-null vehicle UUID
 * @author Piero Francisco Montes Chang
 */
public record VehicleId(UUID value) {

    public VehicleId {
        if (value == null) {
            throw new IllegalArgumentException("Vehicle ID must not be null.");
        }
    }
}