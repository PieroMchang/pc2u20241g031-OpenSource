package com.tesla.platform.u20241g031.shared.domain.model.valueobjects;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Shared Value Object representing a scheduled departure and estimated arrival window.
 *
 * @param departureTime    scheduled departure date and time
 * @param estimatedArrival estimated arrival date and time (must be strictly after departureTime + 1 hour)
 * @author Piero Francisco Montes Chang
 */
public record TemporalWindow(LocalDateTime departureTime, LocalDateTime estimatedArrival) {

    private static final LocalTime NIGHT_START = LocalTime.of(20, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    public TemporalWindow {
        if (departureTime == null || estimatedArrival == null) {
            throw new IllegalArgumentException("Departure time and estimated arrival must not be null.");
        }
        if (!estimatedArrival.isAfter(departureTime.plusHours(1))) {
            throw new IllegalArgumentException(
                    "Estimated arrival must be strictly after departure time plus 1 hour.");
        }
    }

    /**
     * Checks whether the departure time falls within night operation hours (20:00 to 06:00).
     *
     * @return {@code true} if departure occurs between 20:00 and 06:00 inclusive, {@code false} otherwise
     */
    public boolean isNightOperation() {
        LocalTime departureLocalTime = departureTime.toLocalTime();
        return !departureLocalTime.isBefore(NIGHT_START) || !departureLocalTime.isAfter(NIGHT_END);
    }
}