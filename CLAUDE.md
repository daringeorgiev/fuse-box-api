# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Start the app (requires PostgreSQL running)
mvn spring-boot:run

# Build a production JAR
mvn clean package

# Compile only (fast type-check)
mvn compile

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=PanelServiceTest

# Start PostgreSQL via Docker (run before the app)
docker-compose up -d db
```

No test runner is configured yet beyond `spring-boot-starter-test`.

## Architecture

Spring Boot 4 / Java 21 REST API. The request flow is:

```
Controller → Service → Repository (JPA) → PostgreSQL
                ↕
            MapStruct mapper
                ↕
           DTO ↔ Entity
```

**Key conventions:**
- Services are `@Transactional(readOnly = true)` at the class level; write methods override with `@Transactional`.
- `PanelService.getPanel(UUID)` is package-private so `FuseService` can reuse it for FK resolution without going through the full response DTO path.
- MapStruct mappers are Spring-managed beans (`componentModel = "spring"`). The annotation processor order in `pom.xml` is intentional: Lombok must process before MapStruct so generated getters/setters are visible to MapStruct.
- Entities use `@GeneratedValue(strategy = GenerationType.UUID)` (Hibernate 6+) and Hibernate's `@CreationTimestamp` / `@UpdateTimestamp` — do not set these fields manually.
- `ddl-auto: validate` means Hibernate validates the schema against Flyway-managed tables on startup; never use `create` or `update`.

## Database migrations

Migrations live in `src/main/resources/db/migration/` and follow Flyway's `V{n}__{description}.sql` naming. Always create a new versioned file — never edit an existing migration.

The local Docker DB defaults (used when env vars are absent):
- Host: `localhost:5432`, DB: `fusebox`, user/pass: `fusebox`

## Domain model

- **Panel** — a physical breaker panel (`numRows × fusesPerRow` defines capacity).
- **Fuse** — a breaker slot belonging to a panel; `position` (0-based integer) determines slot order. Fuses are always fetched ordered by `position` via `FuseRepository.findByPanelIdOrderByPosition`.
- `FuseService.reorder()` reassigns `position` to each fuse's index in the submitted `orderedIds` list within a single transaction.

## API

- Panels: `/api/panels` (CRUD)
- Fuses: `/api/panels/{panelId}/fuses` (CRUD + `PUT /reorder`)
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- `GlobalExceptionHandler` maps `ResourceNotFoundException` → 404, `MethodArgumentNotValidException` → 400 with per-field errors, anything else → 500.
