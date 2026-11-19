package com.tesla.platform.u20241g031.fleet.infrastructure.embeddables;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * JPA Embeddable mapping route coordinates and total distance with distinct origin/destination columns.
 *
 * @author Piero Francisco Montes Chang
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteDataPersistenceEmbeddable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "origin_latitude", nullable = false)),
            @AttributeOverride(name = "longitude", column = @Column(name = "origin_longitude", nullable = false))
    })
    private GeoCoordinatesPersistenceEmbeddable originCoordinates;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "destination_latitude", nullable = false)),
            @AttributeOverride(name = "longitude", column = @Column(name = "destination_longitude", nullable = false))
    })
    private GeoCoordinatesPersistenceEmbeddable destinationCoordinates;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDistanceKm;
}