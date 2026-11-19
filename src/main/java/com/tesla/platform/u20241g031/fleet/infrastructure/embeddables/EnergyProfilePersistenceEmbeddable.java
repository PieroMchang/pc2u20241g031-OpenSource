package com.tesla.platform.u20241g031.fleet.infrastructure.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * JPA Embeddable mapping energy consumption profile values.
 *
 * @author Piero Francisco Montes Chang
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnergyProfilePersistenceEmbeddable {

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal baseKwh;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal weatherMultiplier;
}