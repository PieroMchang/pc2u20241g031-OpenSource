package com.tesla.platform.u20241g031.fleet.interfaces.rest.transform;

import com.tesla.platform.u20241g031.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u20241g031.fleet.interfaces.rest.resources.FleetDispatchResource;

/**
 * Complex Assembler flattening a {@link FleetDispatch} aggregate into a {@link FleetDispatchResource}.
 *
 * @author Piero Francisco Montes Chang
 */
public final class FleetDispatchResourceFromEntityAssembler {

    private FleetDispatchResourceFromEntityAssembler() {
    }

    /**
     * Converts a FleetDispatch aggregate into its flattened REST resource representation,
     * including calculated totalRequiredEnergy and waypointCount.
     *
     * @param entity domain aggregate
     * @return flattened REST response resource
     */
    public static FleetDispatchResource toResourceFromEntity(FleetDispatch entity) {
        return new FleetDispatchResource(
                entity.getId(),
                entity.getDispatchCode().code(),
                entity.getVehicleId().value().toString(),
                entity.getRouteData().originCoordinates().latitude(),
                entity.getRouteData().originCoordinates().longitude(),
                entity.getRouteData().destinationCoordinates().latitude(),
                entity.getRouteData().destinationCoordinates().longitude(),
                entity.getRouteData().totalDistanceKm(),
                entity.getTemporalWindow().departureTime().toString(),
                entity.getTemporalWindow().estimatedArrival().toString(),
                entity.calculateTotalRequiredEnergy(),
                entity.getWaypointCount(),
                entity.getStatus().name(),
                entity.getSafetyNotes()
        );
    }
}