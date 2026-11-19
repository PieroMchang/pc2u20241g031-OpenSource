package com.tesla.platform.u20241g031.fleet.infrastructure.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA Embeddable representing a persisted intermediate waypoint element.
 *
 * @author Piero Francisco Montes Chang
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WaypointPersistenceEmbeddable {

    @Column(nullable = false)
    private Integer stopSequence;

    @Column(nullable = false, length = 150)
    private String locationName;
}