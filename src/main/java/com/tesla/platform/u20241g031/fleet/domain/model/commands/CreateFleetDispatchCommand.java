package com.tesla.platform.u20241g031.fleet.domain.model.commands;

import com.tesla.platform.u20241g031.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.TemporalWindow;

import java.util.Set;

/**
 * Command encapsulating all validated data required to create a FleetDispatch aggregate.
 *
 * @author Piero Francisco Montes Chang
 */
public record CreateFleetDispatchCommand(
        DispatchCode dispatchCode,
        VehicleId vehicleId,
        RouteData routeData,
        TemporalWindow temporalWindow,
        EnergyProfile energyProfile,
        Set<Waypoint> waypoints,
        DispatchStatus status,
        String safetyNotes
) {
}