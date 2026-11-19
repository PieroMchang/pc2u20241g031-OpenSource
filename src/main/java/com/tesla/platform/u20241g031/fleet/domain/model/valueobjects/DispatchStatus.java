package com.tesla.platform.u20241g031.fleet.domain.model.valueobjects;

/**
 * Enumeration of valid lifecycle statuses for a FleetDispatch.
 *
 * @author Piero Francisco Montes Chang
 */
public enum DispatchStatus {
    PENDING,
    EN_ROUTE,
    CHARGING,
    COMPLETED,
    CANCELLED
}