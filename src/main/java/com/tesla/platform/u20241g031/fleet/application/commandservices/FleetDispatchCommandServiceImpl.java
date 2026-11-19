package com.tesla.platform.u20241g031.fleet.application.internal.commandservices;

import com.tesla.platform.u20241g031.fleet.application.commandservices.FleetDispatchCommandService;
import com.tesla.platform.u20241g031.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u20241g031.fleet.domain.model.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u20241g031.fleet.domain.repositories.FleetDispatchRepository;
import com.tesla.platform.u20241g031.shared.application.result.ApplicationError;
import com.tesla.platform.u20241g031.shared.application.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link FleetDispatchCommandService} enforcing composite uniqueness
 * and persisting new FleetDispatch aggregates.
 *
 * @author Piero Francisco Montes Chang
 */
@Service
public class FleetDispatchCommandServiceImpl implements FleetDispatchCommandService {

    private final FleetDispatchRepository fleetDispatchRepository;

    public FleetDispatchCommandServiceImpl(FleetDispatchRepository fleetDispatchRepository) {
        this.fleetDispatchRepository = fleetDispatchRepository;
    }

    @Override
    @Transactional
    public Result<FleetDispatch> handle(CreateFleetDispatchCommand command) {
        if (fleetDispatchRepository.existsByDispatchCodeAndVehicleId(command.dispatchCode(), command.vehicleId())) {
            return Result.failure(ApplicationError.conflict(
                    "fleet.dispatch.duplicate",
                    command.dispatchCode().code(),
                    command.vehicleId().value().toString()
            ));
        }

        try {
            var fleetDispatch = new FleetDispatch(command);
            var savedDispatch = fleetDispatchRepository.save(fleetDispatch);
            return Result.success(savedDispatch);
        } catch (IllegalArgumentException ex) {
            return Result.failure(ApplicationError.validation(ex.getMessage()));
        }
    }
}