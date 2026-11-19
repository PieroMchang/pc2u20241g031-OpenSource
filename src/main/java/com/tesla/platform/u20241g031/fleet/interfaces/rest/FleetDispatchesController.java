package com.tesla.platform.u20241g031.fleet.interfaces.rest;

import com.tesla.platform.u20241g031.fleet.application.commandservices.FleetDispatchCommandService;
import com.tesla.platform.u20241g031.fleet.interfaces.rest.resources.CreateFleetDispatchResource;
import com.tesla.platform.u20241g031.fleet.interfaces.rest.resources.FleetDispatchResource;
import com.tesla.platform.u20241g031.fleet.interfaces.rest.transform.CreateFleetDispatchCommandFromResourceAssembler;
import com.tesla.platform.u20241g031.fleet.interfaces.rest.transform.FleetDispatchResourceFromEntityAssembler;
import com.tesla.platform.u20241g031.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller exposing endpoints for Tesla Autonomous Fleet Dispatch Management.
 *
 * @author Piero Francisco Montes Chang
 */
@RestController
@RequestMapping(value = "/api/v1/fleet-dispatches", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Fleet Dispatches", description = "Endpoints for Tesla Autonomous Fleet Dispatch Management")
public class FleetDispatchesController {

    private final FleetDispatchCommandService fleetDispatchCommandService;
    private final ResponseEntityAssembler responseEntityAssembler;

    public FleetDispatchesController(
            FleetDispatchCommandService fleetDispatchCommandService,
            ResponseEntityAssembler responseEntityAssembler
    ) {
        this.fleetDispatchCommandService = fleetDispatchCommandService;
        this.responseEntityAssembler = responseEntityAssembler;
    }

    /**
     * Registers a new autonomous FleetDispatch.
     *
     * @param resource nested dispatch creation payload
     * @return HTTP 201 Created with flattened {@link FleetDispatchResource}, or localized HTTP 400/409 error
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new Fleet Dispatch",
            description = "Creates and persists a Tesla autonomous fleet dispatch plan, computing total required energy and waypoint count."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Fleet dispatch created successfully",
                    content = @Content(schema = @Schema(implementation = FleetDispatchResource.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input payload or domain rule violation",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Duplicate dispatchCode and vehicleId combination",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity<?> createFleetDispatch(@Valid @RequestBody CreateFleetDispatchResource resource) {
        var command = CreateFleetDispatchCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = fleetDispatchCommandService.handle(command);
        return responseEntityAssembler.toCreatedResponse(
                result,
                FleetDispatchResourceFromEntityAssembler::toResourceFromEntity
        );
    }
}