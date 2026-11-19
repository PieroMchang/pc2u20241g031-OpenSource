package com.tesla.platform.u20241g031.fleet.application.commandservices;

import com.tesla.platform.u20241g031.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u20241g031.fleet.domain.model.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u20241g031.shared.application.result.Result;

/**
 * CQRS command service contract for FleetDispatch write operations.
 *
 * @author Piero Francisco Montes Chang
 */
public interface FleetDispatchCommandService {

    /**
     * Handles the creation of a new FleetDispatch aggregate.
     *
     * @param command creation command
     * @return {@link Result} containing the saved {@link FleetDispatch} or an application error
     */
    Result<FleetDispatch> handle(CreateFleetDispatchCommand command);
}