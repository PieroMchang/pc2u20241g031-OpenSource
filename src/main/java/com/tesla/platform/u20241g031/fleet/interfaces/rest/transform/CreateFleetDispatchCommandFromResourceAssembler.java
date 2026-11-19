package com.tesla.platform.u20241g031.fleet.interfaces.rest.transform;

import com.tesla.platform.u20241g031.fleet.domain.model.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u20241g031.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241g031.fleet.interfaces.rest.resources.CreateFleetDispatchResource;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.GeoCoordinates;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.TemporalWindow;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Assembler translating a {@link CreateFleetDispatchResource} into a {@link CreateFleetDispatchCommand}.
 *
 * @author Piero Francisco Montes Chang
 */
public final class CreateFleetDispatchCommandFromResourceAssembler {

    private CreateFleetDispatchCommandFromResourceAssembler() {
    }

    /**
     * Converts the incoming nested REST request into a domain command with validated Value Objects.
     *
     * @param resource REST request payload
     * @return domain creation command
     */
    public static CreateFleetDispatchCommand toCommandFromResource(CreateFleetDispatchResource resource) {
        var dispatchCode = new DispatchCode(resource.dispatchCode());
        var vehicleId = new VehicleId(resource.vehicleId());

        var origin = new GeoCoordinates(
                resource.routeData().originCoordinates().latitude(),
                resource.routeData().originCoordinates().longitude()
        );
        var destination = new GeoCoordinates(
                resource.routeData().destinationCoordinates().latitude(),
                resource.routeData().destinationCoordinates().longitude()
        );
        var routeData = new RouteData(origin, destination, resource.routeData().totalDistanceKm());

        var temporalWindow = new TemporalWindow(
                resource.temporalWindow().departureTime(),
                resource.temporalWindow().estimatedArrival()
        );

        var energyProfile = new EnergyProfile(
                resource.energyProfile().baseKwh(),
                resource.energyProfile().weatherMultiplier()
        );

        Set<Waypoint> waypoints = resource.waypoints().stream()
                .map(wp -> new Waypoint(wp.stopSequence(), wp.locationName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        DispatchStatus status = DispatchStatus.PENDING;
        if (resource.status() != null && !resource.status().isBlank()) {
            try {
                status = DispatchStatus.valueOf(resource.status().trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid dispatch status: " + resource.status());
            }
        }

        return new CreateFleetDispatchCommand(
                dispatchCode,
                vehicleId,
                routeData,
                temporalWindow,
                energyProfile,
                waypoints,
                status,
                resource.safetyNotes()
        );
    }
}