package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import java.math.BigDecimal;

/**
 * Flattened REST response resource representing a persisted FleetDispatch with computed fields.
 *
 * @author Piero Francisco Montes Chang
 */
public record FleetDispatchResource(
        Long id,
        String dispatchCode,
        String vehicleId,
        Double originLat,
        Double originLng,
        Double destLat,
        Double destLng,
        BigDecimal totalDistanceKm,
        String departureTime,
        String estimatedArrival,
        BigDecimal totalRequiredEnergy,
        Integer waypointCount,
        String status,
        String safetyNotes
) {
}