package com.tesla.platform.u20241g031.shared.domain.model.valueobjects;

/**
 * Shared Value Object representing validated geographic coordinates.
 *
 * @param latitude  geographic latitude between -90.0 and 90.0
 * @param longitude geographic longitude between -180.0 and 180.0
 * @author Piero Francisco Montes Chang
 */
public record GeoCoordinates(Double latitude, Double longitude) {

    public GeoCoordinates {
        if (latitude == null || latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees.");
        }
        if (longitude == null || longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees.");
        }
    }
}