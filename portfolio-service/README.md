# Portfolio Service

A Spring Boot microservice responsible for managing user investment portfolios. It provides endpoints to retrieve portfolio holdings by user ID or for the currently authenticated user.

## Ports & Endpoints

- **Service Port:** `8084`
- **Base Path:** `/api/v1/portfolio`
- **Swagger UI (direct):** `http://localhost:8084/swagger-ui/index.html`
- **Swagger UI (via Gateway):** `http://localhost:8080/swagger-ui/index.html` → select **portfolio-service**

## API Reference

All endpoints require a valid JWT token.

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/portfolio/{userId}` | Get portfolio by userId |
| GET | `/api/v1/portfolio/me` | Get login user portfolio |

## Environment Variables

| Variable | Description |
|----------|-------------|
| `SPRING_DATASOURCE_URL` | MySQL JDBC URL |
| `SPRING_DATASOURCE_USERNAME` | DB username |
| `SPRING_DATASOURCE_PASSWORD` | DB password |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Hibernate DDL mode |
| `SPRING_DATA_REDIS_HOST` | Redis host |
| `SPRING_DATA_REDIS_PORT` | Redis port |
| `SPRING_KAFKA_BOOTSTRAP_SERVERS` | Kafka bootstrap servers |

## Dependencies

- MySQL (`mysql:3306/trading`)
- Redis (`redis:6379`)
- Kafka (`kafka:29092`)

## Running Locally

```bash
# Ensure dependencies are up (from project root)
docker compose up -d mysql redis kafka

# Run the service
./mvnw spring-boot:run