# Fuse Box API

REST API for managing electrical fuse panels and circuit breakers, built with Spring Boot 4 and PostgreSQL.

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose

## Running PostgreSQL locally

```bash
docker-compose up -d db
```

Starts PostgreSQL 16 on port `5432` with:
- Database: `fusebox`
- Username: `fusebox`
- Password: `fusebox`

Flyway runs migrations automatically on startup.

## Running the application

```bash
mvn spring-boot:run
```

Or with explicit environment variables:

```bash
DB_HOST=localhost DB_PORT=5432 DB_NAME=fusebox DB_USERNAME=fusebox DB_PASSWORD=fusebox mvn spring-boot:run
```

## Building a JAR

```bash
mvn clean package
java -jar target/fuse-box-api-0.0.1-SNAPSHOT.jar
```

## Swagger UI

Browse the interactive API docs at:

**http://localhost:8080/swagger-ui.html**

## API Overview

### Panels — `/api/panels`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/panels` | List all panels |
| GET | `/api/panels/{id}` | Get panel by ID |
| POST | `/api/panels` | Create a panel |
| PUT | `/api/panels/{id}` | Update a panel |
| DELETE | `/api/panels/{id}` | Delete a panel |

### Fuses — `/api/panels/{panelId}/fuses`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/panels/{panelId}/fuses` | List fuses (ordered by position) |
| GET | `/api/panels/{panelId}/fuses/{id}` | Get fuse by ID |
| POST | `/api/panels/{panelId}/fuses` | Add a fuse to a panel |
| PUT | `/api/panels/{panelId}/fuses/{id}` | Update a fuse |
| DELETE | `/api/panels/{panelId}/fuses/{id}` | Remove a fuse |
| PUT | `/api/panels/{panelId}/fuses/reorder` | Reorder fuses |
