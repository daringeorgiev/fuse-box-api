# Fuse Box API

REST API for managing electrical fuse panels and circuit breakers, built with Spring Boot 4 and PostgreSQL.

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose

## Firebase Setup

The API uses Firebase Authentication to verify user identity.

### 1. Create a Firebase project

1. Go to the [Firebase Console](https://console.firebase.google.com) and create a new project.
2. In the project, go to **Authentication → Sign-in method** and enable the providers you need (e.g. Google, Email/Password).

### 2. Generate a service account key

1. In the Firebase Console, go to **Project Settings → Service accounts**.
2. Click **Generate new private key** and confirm.
3. Save the downloaded JSON file as `service-account.json` in the project root.

> **Never commit `service-account.json` to version control.** It is already listed in `.gitignore`.

### 3. Configure the path

By default the app looks for `service-account.json` in the working directory. Override it with an environment variable:

```bash
FIREBASE_SERVICE_ACCOUNT_PATH=/path/to/service-account.json mvn spring-boot:run
```

### 4. Assign a default panel (optional)

If you want unauthenticated users to see a read-only demo panel, set `is_default = true` on the relevant row and leave its `user_id` as `NULL`. Authenticated users always see their own panels.

To claim existing panels that were created before auth was enabled, update them with your Firebase UID (visible in **Firebase Console → Authentication → Users**):

```sql
UPDATE panel SET user_id = '<your-firebase-uid>' WHERE user_id IS NULL;
```

---

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
