package com.tesla.platform.u20241g031.fleet.interfaces.rest.resources;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Nested REST request resource for energy consumption parameters.
 *
 * @author Piero Francisco Montes Chang
 */
public record EnergyProfileResource(
        @NotNull(message = "{validation.notNull}")
        @DecimalMin(value = "0.0", inclusive = false, message = "{validation.energy.baseKwh}")
        BigDecimal baseKwh,

        @NotNull(message = "{validation.notNull}")
        @DecimalMin(value = "1.0", inclusive = true, message = "{validation.energy.weatherMultiplier}")
        BigDecimal weatherMultiplier
) {
}