package com.tesla.platform.u20241g031.fleet.domain.model.entities;

import java.util.Objects;

/**
 * Local domain entity representing an intermediate stop (waypoint) in a FleetDispatch.
 *
 * @author Piero Francisco Montes Chang
 */
public class Waypoint {

    private final Integer stopSequence;
    private final String locationName;

    /**
     * Constructs a validated Waypoint instance.
     *
     * @param stopSequence positive sequence order of the stop
     * @param locationName non-null, non-blank name of the stop location
     */
    public Waypoint(Integer stopSequence, String locationName) {
        if (stopSequence == null || stopSequence <= 0) {
            throw new IllegalArgumentException("Waypoint stop sequence must be a positive integer.");
        }
        if (locationName == null || locationName.isBlank()) {
            throw new IllegalArgumentException("Waypoint location name must not be null or blank.");
        }
        this.stopSequence = stopSequence;
        this.locationName = locationName;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Waypoint waypoint)) return false;
        return Objects.equals(stopSequence, waypoint.stopSequence)
                && Objects.equals(locationName, waypoint.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopSequence, locationName);
    }
}