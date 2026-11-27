# erp

ERP built with [Quarkus](https://quarkus.io/). The domain model uses the [Microsoft Common Data Model (CDM)](https://github.com/microsoft/CDM) as reference.

## Stack

- Java 21 + Quarkus 3.40
- Maven (with Maven Wrapper)
- PostgreSQL + Hibernate ORM with Panache
- Flyway for database migrations
- REST (Quarkus REST + Jackson), Hibernate Validator, SmallRye OpenAPI and Health

## Prerequisites

- JDK 21
- Docker or Podman (Dev Services starts PostgreSQL automatically in dev and tests)

## Running in dev mode

```shell
./mvnw quarkus:dev
```

| Endpoint | Description |
|---|---|
| <http://localhost:8080/q/dev/> | Dev UI (dev only) |
| <http://localhost:8080/q/swagger-ui> | Swagger UI (dev only) |
| <http://localhost:8080/q/openapi> | OpenAPI document |
| <http://localhost:8080/q/health> | Health check (liveness + readiness, includes the database) |

## Tests and formatting

```shell
./mvnw verify            # tests + formatting check (Spotless)
./mvnw spotless:apply    # formats the code (palantir-java-format) and the pom.xml
```

CI (GitHub Actions) runs `./mvnw verify` on every push to `main` and on pull requests.

## Database

- Migrations live in `src/main/resources/db/migration`, following the Flyway convention (`V1__description.sql`), and run at startup.
- Hibernate only **validates** the schema (`schema-management.strategy=validate`); every table change must come from a migration.

## Packaging

```shell
./mvnw package                 # produces target/quarkus-app/
java -jar target/quarkus-app/quarkus-run.jar
./mvnw package -Dnative        # native executable (requires GraalVM or a container build)
```

Dockerfiles are in `src/main/docker/`.

## Quarkus guides

- [REST](https://quarkus.io/guides/rest)
- [Hibernate ORM with Panache](https://quarkus.io/guides/hibernate-orm-panache)
- [Flyway](https://quarkus.io/guides/flyway)
- [Validation](https://quarkus.io/guides/validation)
- [OpenAPI and Swagger UI](https://quarkus.io/guides/openapi-swaggerui)
- [SmallRye Health](https://quarkus.io/guides/smallrye-health)
- [Dev Services for databases](https://quarkus.io/guides/databases-dev-services)
