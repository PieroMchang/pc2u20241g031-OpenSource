package com.tesla.platform.u20241g031.fleet.infrastructure.assemblers;

import com.tesla.platform.u20241g031.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u20241g031.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.EnergyProfilePersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.GeoCoordinatesPersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.RouteDataPersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.TemporalWindowPersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.embeddables.WaypointPersistenceEmbeddable;
import com.tesla.platform.u20241g031.fleet.infrastructure.entities.FleetDispatchPersistenceEntity;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.GeoCoordinates;
import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.TemporalWindow;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Bidirectional assembler translating between {@link FleetDispatch} domain aggregates
 * and {@link FleetDispatchPersistenceEntity} JPA entities.
 *
 * @author Piero Francisco Montes Chang
 */
public final class FleetDispatchPersistenceAssembler {

    private FleetDispatchPersistenceAssembler() {
    }

    /**
     * Maps a domain aggregate to a JPA persistence entity.
     *
     * @param aggregate domain aggregate
     * @return persistence entity
     */
    public static FleetDispatchPersistenceEntity toPersistenceEntity(FleetDispatch aggregate) {
        var entity = new FleetDispatchPersistenceEntity();
        entity.setDispatchCode(aggregate.getDispatchCode().code());
        entity.setVehicleId(aggregate.getVehicleId().value().toString());
        entity.setRouteData(new RouteDataPersistenceEmbeddable(
                new GeoCoordinatesPersistenceEmbeddable(
                        aggregate.getRouteData().originCoordinates().latitude(),
                        aggregate.getRouteData().originCoordinates().longitude()
                ),
                new GeoCoordinatesPersistenceEmbeddable(
                        aggregate.getRouteData().destinationCoordinates().latitude(),
                        aggregate.getRouteData().destinationCoordinates().longitude()
                ),
                aggregate.getRouteData().totalDistanceKm()
        ));
        entity.setTemporalWindow(new TemporalWindowPersistenceEmbeddable(
                aggregate.getTemporalWindow().departureTime(),
                aggregate.getTemporalWindow().estimatedArrival()
        ));
        entity.setEnergyProfile(new EnergyProfilePersistenceEmbeddable(
                aggregate.getEnergyProfile().baseKwh(),
                aggregate.getEnergyProfile().weatherMultiplier()
        ));
        Set<WaypointPersistenceEmbeddable> waypoints = aggregate.getWaypoints().stream()
                .map(wp -> new WaypointPersistenceEmbeddable(wp.getStopSequence(), wp.getLocationName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        entity.setWaypoints(waypoints);
        entity.setStatus(aggregate.getStatus());
        entity.setSafetyNotes(aggregate.getSafetyNotes());
        return entity;
    }

    /**
     * Maps a persisted JPA entity back into a {@link FleetDispatch} domain aggregate.
     *
     * @param entity persistence entity
     * @return reconstructed domain aggregate
     */
    public static FleetDispatch toDomainAggregate(FleetDispatchPersistenceEntity entity) {
        var origin = new GeoCoordinates(
                entity.getRouteData().getOriginCoordinates().getLatitude(),
                entity.getRouteData().getOriginCoordinates().getLongitude()
        );
        var destination = new GeoCoordinates(
                entity.getRouteData().getDestinationCoordinates().getLatitude(),
                entity.getRouteData().getDestinationCoordinates().getLongitude()
        );
        var routeData = new RouteData(origin, destination, entity.getRouteData().getTotalDistanceKm());
        var temporalWindow = new TemporalWindow(
                entity.getTemporalWindow().getDepartureTime(),
                entity.getTemporalWindow().getEstimatedArrival()
        );
        var energyProfile = new EnergyProfile(
                entity.getEnergyProfile().getBaseKwh(),
                entity.getEnergyProfile().getWeatherMultiplier()
        );
        Set<Waypoint> waypoints = entity.getWaypoints().stream()
                .map(wp -> new Waypoint(wp.getStopSequence(), wp.getLocationName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return new FleetDispatch(
                entity.getId(),
                new DispatchCode(entity.getDispatchCode()),
                new VehicleId(UUID.fromString(entity.getVehicleId())),
                routeData,
                temporalWindow,
                energyProfile,
                waypoints,
                entity.getStatus(),
                entity.getSafetyNotes(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}