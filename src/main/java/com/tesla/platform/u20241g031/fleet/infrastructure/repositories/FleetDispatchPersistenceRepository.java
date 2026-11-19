package com.tesla.platform.u20241g031.fleet.infrastructure.repositories;

import com.tesla.platform.u20241g031.fleet.infrastructure.entities.FleetDispatchPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for {@link FleetDispatchPersistenceEntity}.
 *
 * @author Piero Francisco Montes Chang
 */
@Repository
public interface FleetDispatchPersistenceRepository extends JpaRepository<FleetDispatchPersistenceEntity, Long> {

    /**
     * Verifies if a fleet dispatch exists with the given dispatch code and vehicle UUID string.
     *
     * @param dispatchCode dispatch code string
     * @param vehicleId    vehicle UUID string
     * @return {@code true} if a matching entity exists
     */
    boolean existsByDispatchCodeAndVehicleId(String dispatchCode, String vehicleId);
}