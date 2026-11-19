package com.tesla.platform.u20241g031.fleet.infrastructure.adapters;

import com.tesla.platform.u20241g031.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241g031.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241g031.fleet.domain.repositories.FleetDispatchRepository;
import com.tesla.platform.u20241g031.fleet.infrastructure.assemblers.FleetDispatchPersistenceAssembler;
import com.tesla.platform.u20241g031.fleet.infrastructure.repositories.FleetDispatchPersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence adapter bridging the domain {@link FleetDispatchRepository} port
 * with Spring Data JPA.
 *
 * @author Piero Francisco Montes Chang
 */
@Repository
public class FleetDispatchRepositoryImpl implements FleetDispatchRepository {

    private final FleetDispatchPersistenceRepository persistenceRepository;

    public FleetDispatchRepositoryImpl(FleetDispatchPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public boolean existsByDispatchCodeAndVehicleId(DispatchCode dispatchCode, VehicleId vehicleId) {
        return persistenceRepository.existsByDispatchCodeAndVehicleId(
                dispatchCode.code(),
                vehicleId.value().toString()
        );
    }

    @Override
    public FleetDispatch save(FleetDispatch fleetDispatch) {
        var persistenceEntity = FleetDispatchPersistenceAssembler.toPersistenceEntity(fleetDispatch);
        var savedEntity = persistenceRepository.save(persistenceEntity);
        return FleetDispatchPersistenceAssembler.toDomainAggregate(savedEntity);
    }
}