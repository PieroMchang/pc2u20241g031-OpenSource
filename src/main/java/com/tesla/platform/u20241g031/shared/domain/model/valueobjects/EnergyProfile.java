package com.tesla.platform.u20241g031.shared.domain.model.valueobjects;

import java.math.BigDecimal;

/**
 * Shared Value Object representing energy consumption parameters for an autonomous dispatch.
 *
 * @param baseKwh           base energy consumption in kWh (strictly greater than 0)
 * @param weatherMultiplier weather impact multiplier (greater than or equal to 1.0)
 * @author Piero Francisco Montes Chang
 */
public record EnergyProfile(BigDecimal baseKwh, BigDecimal weatherMultiplier) {

    public EnergyProfile {
        if (baseKwh == null || baseKwh.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base kWh must be strictly greater than zero.");
        }
        if (weatherMultiplier == null || weatherMultiplier.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("Weather multiplier must be greater than or equal to 1.0.");
        }
    }

    /**
     * Calculates the total required energy by multiplying base kWh by the weather multiplier.
     *
     * @return total required energy in kWh
     */
    public BigDecimal calculateTotalRequiredEnergy() {
        return baseKwh.multiply(weatherMultiplier);
    }
}