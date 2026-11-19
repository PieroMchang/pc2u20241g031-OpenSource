package com.tesla.platform.u20241g031.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI 3 configuration exposing Swagger UI metadata without security schemes.
 *
 * @author Piero Francisco Montes Chang
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * Builds the OpenAPI specification bean for Tesla Autonomous Fleet Management.
     *
     * @return configured {@link OpenAPI} instance
     */
    @Bean
    public OpenAPI teslaFleetManagementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tesla Autonomous Fleet Management API")
                        .description("RESTful API for managing Tesla autonomous fleet dispatches, energy profiles, temporal windows, and route waypoints.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Tesla Fleet Platform")
                        .url("https://www.tesla.com/fleet"));
    }
}