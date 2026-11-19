package com.tesla.platform.u20241g031.fleet.domain.model.aggregates;

import com.tesla.platform.u20241g031.fleet.domain.model.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u20241g031.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241g031.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.TemporalWindow;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * FleetDispatch Aggregate Root managing autonomous fleet dispatch invariants and calculations.
 *
 * @author Piero Francisco Montes Chang
 */
@Getter
public class FleetDispatch extends AbstractDomainAggregateRoot<FleetDispatch> {

    private static final int MIN_WAYPOINTS = 1;
    private static final int MAX_WAYPOINTS = 5;

    private final Long id;
    private final DispatchCode dispatchCode;
    private final VehicleId vehicleId;
    private final RouteData routeData;
    private final TemporalWindow temporalWindow;
    private final EnergyProfile energyProfile;
    private final Set<Waypoint> waypoints;
    private final DispatchStatus status;
    private final String safetyNotes;
    private final Instant createdAt;
    private final Instant updatedAt;

    /**
     * Instantiates a new FleetDispatch aggregate from a {@link CreateFleetDispatchCommand}.
     *
     * @param command creation command
     */
    public FleetDispatch(CreateFleetDispatchCommand command) {
        this(
                null,
                command.dispatchCode(),
                command.vehicleId(),
                command.routeData(),
                command.temporalWindow(),
                command.energyProfile(),
                command.waypoints(),
                command.status(),
                command.safetyNotes(),
                null,
                null
        );
    }

    /**
     * Reconstructs a FleetDispatch aggregate enforcing all domain invariants.
     */
    public FleetDispatch(
            Long id,
            DispatchCode dispatchCode,
            VehicleId vehicleId,
            RouteData routeData,
            TemporalWindow temporalWindow,
            EnergyProfile energyProfile,
            Set<Waypoint> waypoints,
            DispatchStatus status,
            String safetyNotes,
            Instant createdAt,
            Instant updatedAt
    ) {
        if (dispatchCode == null) throw new IllegalArgumentException("Dispatch code must not be null.");
        if (vehicleId == null) throw new IllegalArgumentException("Vehicle ID must not be null.");
        if (routeData == null) throw new IllegalArgumentException("Route data must not be null.");
        if (temporalWindow == null) throw new IllegalArgumentException("Temporal window must not be null.");
        if (energyProfile == null) throw new IllegalArgumentException("Energy profile must not be null.");
        if (waypoints == null || waypoints.size() < MIN_WAYPOINTS || waypoints.size() > MAX_WAYPOINTS) {
            throw new IllegalArgumentException(
                    "A fleet dispatch must contain between " + MIN_WAYPOINTS + " and " + MAX_WAYPOINTS + " waypoints.");
        }

        this.id = id;
        this.dispatchCode = dispatchCode;
        this.vehicleId = vehicleId;
        this.routeData = routeData;
        this.temporalWindow = temporalWindow;
        this.energyProfile = energyProfile;
        this.waypoints = Collections.unmodifiableSet(new LinkedHashSet<>(waypoints));
        this.status = (status != null) ? status : DispatchStatus.PENDING;
        this.safetyNotes = safetyNotes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Calculates the total required energy for this dispatch using its {@link EnergyProfile}.
     *
     * @return total required energy in kWh
     */
    public BigDecimal calculateTotalRequiredEnergy() {
        return energyProfile.calculateTotalRequiredEnergy();
    }

    /**
     * Returns the total count of intermediate waypoints registered for this dispatch.
     *
     * @return waypoint count
     */
    public Integer getWaypointCount() {
        return waypoints.size();
    }
}