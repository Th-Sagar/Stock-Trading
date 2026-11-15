# API Gateway

A Spring Cloud Gateway microservice serving as the single entry point to all backend microservices. It routes requests, aggregates Swagger documentation, and enforces JWT authentication through a custom `JwtAuthFilter`.

## Ports & Endpoints

- **Gateway Port:** `8080`
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **Actuator Health:** `http://localhost:8080/actuator/health`

## Routing Table

### Swagger Docs Routes

| Route ID | Path | Target |
|----------|------|--------|
| user-service-docs | `/user-service/v3/api-docs` | `http://localhost:8081` |
| market-data-service-docs | `/market-data-service/v3/api-docs` | `http://localhost:8082` |
| order-service-docs | `/order-service/v3/api-docs` | `http://localhost:8083` |
| portfolio-service-docs | `/portfolio-service/v3/api-docs` | `http://localhost:8084` |

### Public Routes (no JWT)

| Route ID | Path | Target |
|----------|------|--------|
| user-service-public | `/api/v1/users/register`, `/api/v1/users/login` | `http://localhost:8081` |

### Protected Routes (`JwtAuthFilter`)

| Route ID | Path | Target |
|----------|------|--------|
| user-service | `/api/v1/users/**` | `http://localhost:8081` |
| market-data-service | `/api/v1/market/**` | `http://localhost:8082` |
| order-service | `/api/v1/orders/**` | `http://localhost:8083` |
| portfolio-service | `/api/v1/portfolio/**` | `http://localhost:8084` |

### WebSocket Route

| Route ID | Path | Target |
|----------|------|--------|
| market-websocket | `/ws/**` | `ws://localhost:8082` |

## Configuration

```yaml
server:
  port: 8080

jwt:
  secret: 

springdoc:
  swagger-ui:
    path: /swagger-ui.html
    urls:
      - name: user-service
        url: /user-service/v3/api-docs
      - name: market-data-service
        url: /market-data-service/v3/api-docs
      - name: order-service
        url: /order-service/v3/api-docs
      - name: portfolio-service
        url: /portfolio-service/v3/api-docs