package com.tesla.platform.u20241g031.fleet.domain.repositories;

import com.tesla.platform.u20241g031.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.VehicleId;

/**
 * Pure domain repository interface for FleetDispatch persistence operations.
 *
 * @author Piero Francisco Montes Chang
 */
public interface FleetDispatchRepository {

    /**
     * Checks whether a dispatch already exists with the exact combination of dispatch code and vehicle ID.
     *
     * @param dispatchCode dispatch code value object
     * @param vehicleId    vehicle identifier value object
     * @return {@code true} if a matching record exists
     */
    boolean existsByDispatchCodeAndVehicleId(DispatchCode dispatchCode, VehicleId vehicleId);

    /**
     * Persists a FleetDispatch aggregate and returns the saved aggregate with generated ID and audit timestamps.
     *
     * @param fleetDispatch aggregate to save
     * @return persisted aggregate
     */
    FleetDispatch save(FleetDispatch fleetDispatch);
}