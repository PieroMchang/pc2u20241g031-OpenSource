package com.tesla.platform.u20241g031.fleet.infrastructure.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * JPA Embeddable mapping departure time and estimated arrival timestamps.
 *
 * @author Piero Francisco Montes Chang
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemporalWindowPersistenceEmbeddable {

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime estimatedArrival;
}