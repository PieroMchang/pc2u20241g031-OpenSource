package com.tesla.platform.u20241g031.fleet.infrastructure.entities;

import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.EnergyProfilePersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.RouteDataPersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.TemporalWindowPersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.WaypointPersistenceEmbeddable;
import com.tesla.platform.u20241g031.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * JPA Persistence Entity for FleetDispatch.
 * Physical naming strategy maps 'fleet_dispatch' to 'fleet_dispatches'
 * and 'fleet_dispatch_waypoint' to 'fleet_dispatch_waypoints'.
 *
 * @author Piero Francisco Montes Chang
 */
@Entity
@Table(
        name = "fleet_dispatch",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_fleet_dispatches_dispatch_code_vehicle_id",
                columnNames = {"dispatch_code", "vehicle_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
public class FleetDispatchPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false, length = 20)
    private String dispatchCode;

    @Column(nullable = false, length = 36)
    private String vehicleId;

    @Embedded
    private RouteDataPersistenceEmbeddable routeData;

    @Embedded
    private TemporalWindowPersistenceEmbeddable temporalWindow;

    @Embedded
    private EnergyProfilePersistenceEmbeddable energyProfile;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "fleet_dispatch_waypoint",
            joinColumns = @JoinColumn(name = "fleet_dispatch_id", nullable = false)
    )
    private Set<WaypointPersistenceEmbeddable> waypoints = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DispatchStatus status;

    @Column(length = 500)
    private String safetyNotes;
}