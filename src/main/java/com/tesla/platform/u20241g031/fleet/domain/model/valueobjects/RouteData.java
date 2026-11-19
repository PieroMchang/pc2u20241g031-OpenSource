package com.tesla.platform.u20241g031.fleet.domain.model.valueobjects;

import com.tesla.platform.u20241g031.shared.domain.model.valueobjects.GeoCoordinates;

import java.math.BigDecimal;

/**
 * Value Object encapsulating origin coordinates, destination coordinates, and total route distance.
 *
 * @param originCoordinates      origin geographic coordinates
 * @param destinationCoordinates destination geographic coordinates
 * @param totalDistanceKm        total distance in kilometers (strictly greater than 0)
 * @author Piero Francisco Montes Chang
 */
public record RouteData(
        GeoCoordinates originCoordinates,
        GeoCoordinates destinationCoordinates,
        BigDecimal totalDistanceKm
) {
    public RouteData {
        if (originCoordinates == null) {
            throw new IllegalArgumentException("Origin coordinates must not be null.");
        }
        if (destinationCoordinates == null) {
            throw new IllegalArgumentException("Destination coordinates must not be null.");
        }
        if (totalDistanceKm == null || totalDistanceKm.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total distance in km must be strictly greater than zero.");
        }
    }
}