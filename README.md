# Tesla Autonomous Fleet Management Platform

## Overview

RESTful API developed with Java 27, Spring Boot 4.1.2, and Spring Data JPA following Domain-Driven Design (DDD), Bounded Context separation (`shared` and `fleet`), Layered Architecture, CQRS, Resource/Assembler, and the Result pattern. It supports Tesla's autonomous fleet operations by registering fleet dispatches with validated geographic coordinates, temporal windows, dynamic energy requirement calculations, intermediate waypoints, composite uniqueness constraints, and localized error responses (English and Spanish).

## Authorship

- **Author:** Piero Francisco Montes Chang
- **Student Code:** u20241g031
- **Course:** Desarrollo de Aplicaciones Open Source (1ASI0729) - Section 7729
- **GitHub Repository:** https://github.com/PieroMchang/pc2u20241g031-OpenSource

## Technical Specifications

- **Java:** 27
- **Framework:** Spring Boot 4.1.2
- **ORM & Database:** Spring Data JPA + MySQL (`tesla` schema)
- **Port:** 8080
- **API Documentation:** OpenAPI / Swagger UI at `http://localhost:8080/swagger-ui.html`

## Endpoint

- `POST /api/v1/fleet-dispatches` — Registers a new FleetDispatch and returns HTTP `201 Created` with a flattened resource including `id`, `totalRequiredEnergy`, and `waypointCount`.